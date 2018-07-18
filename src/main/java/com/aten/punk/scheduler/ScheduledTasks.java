package com.aten.punk.scheduler;


import java.net.URL;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = (1000*60*2))
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
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
			URL url = new URL("https://mn-line-bot.herokuapp.com/callback");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.connect();

			int statusCode = connection.getResponseCode();
			log.info("statusCode : {}", statusCode);
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}