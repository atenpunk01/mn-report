package com.aten.punk.scheduler;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aten.punk.entity.Coin;
import com.aten.punk.entity.Node;
import com.aten.punk.model.CoinModel;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//	public static void main(String[]args) {
//		ScheduledTasks task = new ScheduledTasks();
//		List<Coin> coinList = new ArrayList<Coin>();
//		Coin c = new Coin();
//		c.setCoinName("CDM");
//		coinList.add(c);
//		c = new Coin();
//		c.setCoinName("VYI");
//		coinList.add(c);
//		c = new Coin();
//		c.setCoinName("GOSS");
//		coinList.add(c);
//		List<Node> nodeList = task.masternodeOnline();
//		for(Node node:nodeList) {
//			for(Coin coin:coinList) {
//				if(coin.getCoinName().equals(node.getCoinName())) {
//					System.out.println(coin.getCoinName());
//					break;
//				}
//			}
//		}
//	}

	@Scheduled(fixedRate = (1000*60*1))
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		//		masternodeOnline();
	}

	public List<Node> masternodeOnline() {
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
			URL url = new URL("https://masternodes.online/#masternode-stats");
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
//				StringBuffer response = new StringBuffer();
				boolean chk = false;
				int index = 0;
				Node node = null;
				while ((inputLine = in.readLine()) != null) {
					if(inputLine.contains("</span> <strong><a href=\"/currencies/")) {
						chk = true;
						node = new Node();
						index = 0;
					}
					if(chk && index < 7) {
						index++;
//						response.append(inputLine+"\n");
						
						
//		                <td style="width:100px"> <span style="padding-left: 16px">&nbsp;</span> <strong><a href="/currencies/LIZ/" title="LIZUS masternode detailed stats">LIZUS (LIZ)</a></strong></td>
//		   price   		<td style="width:145px"><span title="0.025638"></span>$0.0256</td>
//		   change  		<td style="width:70px"><span class="text-danger">-13.61 %</span></td>
//		   volume  		<td style="width:120px"><span title="8.27"></span>$8</td>
//		   Marketcap	<td style="width:130px"><span title="0"></span>?</td>
//		   ROI     		<td style="width:100px"><strong><span title="0.00" class="text-info">0.00%</span></strong></td>
//		   Nodes  		<td style="width:80px"><span title="31"></span>31</td>
						
						
						if(index == 1) {
							
						}else if(index == 1) {
							
						}
					}
					if(index==7) {
						chk = false;
						index = 0;
					}
									
				}
				in.close();
//				System.out.println(response.toString());
			} else {
				throw new Exception("Error:(StatusCode)" + statusCode + ", " + connection.getResponseMessage());
			}
			connection.disconnect();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nodeList;
	}
	
//	public List<CoinModel> priceCryptopia(CoinModel coinModel) {
//		List<CoinModel> listCp = new ArrayList<CoinModel>();
//		try {
//			TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
//				@Override
//				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//					return null;
//				}
//
//				@Override
//				public void checkClientTrusted(X509Certificate[] certs,
//						String authType) {
//				}
//
//				@Override
//				public void checkServerTrusted(X509Certificate[] certs,
//						String authType) {
//				}
//			}};
//
//
//			SSLContext sc = SSLContext.getInstance("SSL");
//			sc.init(null, trustAllCerts, new java.security.SecureRandom());
//			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//			URL url = new URL(cryptopiaTickerApi+coinModel.getKey());
//			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//			connection.setDoOutput(true);
//			connection.connect();
//
//			int statusCode = connection.getResponseCode();
//			if (statusCode == 200) {
//				//				String responseMsg = connection.getResponseMessage();
//				//				System.out.println(responseMsg);
//				BufferedReader in = new BufferedReader(
//						new InputStreamReader(connection.getInputStream()));
//				String inputLine;
//				StringBuffer response = new StringBuffer();
//
//				while ((inputLine = in.readLine()) != null) {
//					response.append(inputLine);
//				}
//				in.close();
////				System.out.println(response.toString());
//
//				Gson gson = new Gson();
//				CryptopiaTickerModel obj = gson.fromJson(response.toString(), CryptopiaTickerModel.class);
//				if(obj!=null) {
//					CoinModel model = new CoinModel();
//					String buy = df8.format(obj.getData().getBidPrice());
//					String sell = df8.format(obj.getData().getAskPrice());
//					model.setName(coinModel.getName()+" (CP)");
//					model.setBuy(buy);
//					model.setSell(sell);
//					listCp.add(model);
//				}
//			} else {
//				throw new Exception("Error:(StatusCode)" + statusCode + ", " + connection.getResponseMessage());
//			}
//			connection.disconnect();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return listCp;
//	}

//	public List<CoinModel> priceGraviex(List<CoinModel> listGraviex) {
//		List<CoinModel> listGv = new ArrayList<CoinModel>();
//		try {
//			TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
//				@Override
//				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//					return null;
//				}
//
//				@Override
//				public void checkClientTrusted(X509Certificate[] certs,
//						String authType) {
//				}
//
//				@Override
//				public void checkServerTrusted(X509Certificate[] certs,
//						String authType) {
//				}
//			}};
//
//			SSLContext sc = SSLContext.getInstance("SSL");
//			sc.init(null, trustAllCerts, new java.security.SecureRandom());
//			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//			URL url = new URL(graviexTickerApi);
//			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//			connection.setConnectTimeout(1000*5);
//			connection.setReadTimeout(1000*5);
//			connection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//			connection.addRequestProperty("Accept-Encoding", "gzip, deflate, br");
//			connection.addRequestProperty("Accept-Language", "th,en-US;q=0.7,en;q=0.3");
//			connection.addRequestProperty("Connection", "keep-alive");
//			connection.addRequestProperty("Cookie", "XSRF-TOKEN=AbLt5dZOyRlPWqot3nBGz0fmBh%2F5Sy%2FzkOGRblJUMmo%3D; _peatio_session=5efb8487874d373d2c253b94ae9c9f11; lang=en");
//			connection.addRequestProperty("Host", "graviex.net");
//			connection.addRequestProperty("If-None-Match", "b7fcd10e801effd9b708c4ee625911e7");
//			connection.addRequestProperty("Upgrade-Insecure-Requests", "1");
//			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0");
//			connection.setDoOutput(true);
//			connection.connect();
//
//			int statusCode = connection.getResponseCode();
//			if (statusCode == 200) {
//				//				String responseMsg = connection.getResponseMessage();
//				//				System.out.println(responseMsg);
//				BufferedReader in = new BufferedReader(
//						new InputStreamReader(connection.getInputStream()));
//				String inputLine;
//				StringBuffer response = new StringBuffer();
//
//				while ((inputLine = in.readLine()) != null) {
//					response.append(inputLine);
//				}
//				in.close();
//				//				System.out.println(response.toString());
//				//				System.out.println("listGraviex.size : "+listGraviex.size());
//
//				JSONObject json = new JSONObject(response.toString());
//				for (CoinModel coinModel : listGraviex) {
//					//					System.out.println(coinModel.getKey());
//					JSONObject head = json.getJSONObject(coinModel.getKey());
//					if (head != null) {
//						JSONObject ticker = head.getJSONObject("ticker");
//						String buy = ticker.getString("buy");
//						String sell = ticker.getString("sell");
//						String change = ticker.getString("change");
//						CoinModel model = new CoinModel();
//						buy = df9.format(Double.parseDouble(buy));
//						buy = (buy.substring(0, buy.length() - 1) + "'" + buy.substring(buy.length() - 1, buy.length()));
//						sell = df9.format(Double.parseDouble(sell));
//						sell = (sell.substring(0, sell.length() - 1) + "'" + sell.substring(sell.length() - 1, sell.length()));
//						model.setName(coinModel.getName()+" (GV)");
//						model.setBuy(buy);
//						model.setSell(sell);
//						model.setChange(df.format(Double.parseDouble(change)));
//						listGv.add(model);
//					}
//				}
//			} else {
//				throw new Exception("Error:(StatusCode)" + statusCode + ", " + connection.getResponseMessage());
//			}
//			connection.disconnect();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return listGv;
//	}

//	public List<CoinModel> priceCryptoBridge(List<CoinModel> listCryptoBridge) {
//		List<CoinModel> listCb = new ArrayList<CoinModel>();
//		try {
//			TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
//				@Override
//				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//					return null;
//				}
//
//				@Override
//				public void checkClientTrusted(X509Certificate[] certs,
//						String authType) {
//				}
//
//				@Override
//				public void checkServerTrusted(X509Certificate[] certs,
//						String authType) {
//				}
//			}};
//
//			SSLContext sc = SSLContext.getInstance("SSL");
//			sc.init(null, trustAllCerts, new java.security.SecureRandom());
//			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//			URL url = new URL(cryptoBridgeTickerApi);
//			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//			connection.setConnectTimeout(1000*5);
//			connection.setReadTimeout(1000*5);
//			connection.setDoOutput(true);
//			connection.connect();
//
//			int statusCode = connection.getResponseCode();
//			if (statusCode == 200) {
//				//				String responseMsg = connection.getResponseMessage();
//				//				System.out.println(responseMsg);
//				BufferedReader in = new BufferedReader(
//						new InputStreamReader(connection.getInputStream()));
//				String inputLine;
//				StringBuffer response = new StringBuffer();
//
//				while ((inputLine = in.readLine()) != null) {
//					response.append(inputLine);
//				}
//				in.close();
//				String jsonData = "{\"dataArray\":" + response.toString() + "}";
//				//                System.out.println(jsonData);
//
//				JSONObject json = new JSONObject(jsonData);
//				JSONArray dataArray = json.getJSONArray("dataArray");
//				if (dataArray != null) {
//					for (CoinModel coinModel : listCryptoBridge) {
//						for (int i = 0; i < dataArray.length(); i++) {
//							JSONObject data = dataArray.getJSONObject(i);
//							String id = data.getString("id");
//							if (id != null && id.equals(coinModel.getKey())) {
//								String bid = data.getString("bid");
//								String ask = data.getString("ask");
//								CoinModel model = new CoinModel();
//								model.setName(coinModel.getName()+" (CB)");
//								model.setBuy(df8.format(Double.parseDouble(bid)));
//								model.setSell(df8.format(Double.parseDouble(ask)));
//								model.setChange(df.format(Double.parseDouble("0")));
//								listCb.add(model);
//								break;
//							}
//						}
//					}
//				}
//			} else {
//				throw new Exception("Error:(StatusCode)" + statusCode + ", " + connection.getResponseMessage());
//			}
//			connection.disconnect();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return listCb;
//	}

}