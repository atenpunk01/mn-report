package com.aten.punk.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aten.punk.entity.Node;
import com.aten.punk.repository.NodeRepository;

@Named
@ViewScoped
public class NodeView implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(NodeView.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy",Locale.US);

	@Inject
	private NodeRepository nodeRepository;

	private List<Node> nodes;

	@PostConstruct
	public void init() {
		log.info("##### NodeView #####");
		try {
			List<Node> nodeList = masternodeOnline("GOSS");
			for(Node node:nodeList) {
				log.info("Date : "+sdf.format(node.getDateTime()));
				nodeRepository.saveAndFlush(node);
			}
			nodes = nodeRepository.findAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Node> getNodes() {
		return nodes;
	}

	private List<Node> masternodeOnline(String coin) {
		List<Node> nodeList = new ArrayList<Node>();
		try {
			TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkClientTrusted(X509Certificate[] certs,
						String authType) {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] certs,
						String authType) {
				}
			}};
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			URL url = new URL("https://masternodes.online/currencies/"+coin+"/");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setConnectTimeout(1000*5);
			connection.setReadTimeout(1000*5);
			connection.addRequestProperty("Accept-Language", "th,en-US;q=0.7,en;q=0.3");
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0");
			connection.setDoOutput(true);
			connection.connect();

			int statusCode = connection.getResponseCode();
			if (statusCode == 200) {
				String responseMsg = connection.getResponseMessage();
				System.out.println(responseMsg);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				String inputLine;
				int number = 45;
				String[] response = new String[number];
				boolean chk = false;
				int index = 0;
				while ((inputLine = in.readLine()) != null) {
					if(inputLine.contains("<canvas id=\"canvas\"")) {
						chk = true;
					}
					if(chk) {
						if(index < number) {
							response[index++] = inputLine.trim().replaceAll("	", "");
						}
					}
				}
				in.close();

				String date = "";
				String node = "";
				String roi = "";
				String price = "";
				index = 0;
				for(String data:response) {
					//					System.out.println((index)+" : "+data);
					index++;
					if(index==8) {
						date = (data.split(" ")[1])
								.replaceAll("\\[", "")
								.replaceAll("\\],", "")
								.replaceAll("\"", "");
					}
					if(index==16) {
						node = (data.split(" ")[1])
								.replaceAll("\\[", "")
								.replaceAll("\\],", "")
								.replaceAll("\"", "");
					}
					if(index==24) {
						roi = (data.split(" ")[1])
								.replaceAll("\\[", "")
								.replaceAll("\\],", "")
								.replaceAll("\"", "");
					}
					if(index==37) {
						price = (data.split(" ")[1])
								.replaceAll("\\[", "")
								.replaceAll("\\],", "")
								.replaceAll("\"", "");
					}					
				}
				System.out.println("date : "+date);
				System.out.println("node : "+node);
				System.out.println("roi : "+roi);
				System.out.println("price : "+price);
				String[] dateArray = date.split(",");
				String[] nodeArray = node.split(",");
				String[] roiArray = roi.split(",");
				String[] priceArray = price.split(",");

				for(int i=0;i<dateArray.length;i++) {
					Node model = new Node();
					model.setCoinName(coin);
					model.setDateTime(sdf.parse(dateArray[i]));
					model.setNode(Integer.parseInt(nodeArray[i]));
					model.setPrice(new BigDecimal(priceArray[i]));
					model.setRoi(Double.parseDouble(roiArray[i]));
					nodeList.add(model);
				}
			} else {
				throw new Exception("Error:(StatusCode)" + statusCode + ", " + connection.getResponseMessage());
			}
			connection.disconnect();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nodeList;
	}

}
