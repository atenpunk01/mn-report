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

import com.aten.punk.entity.CoinHeader;
import com.aten.punk.repository.CoinHeaderRepository;

@Named
@ViewScoped
public class HomeView implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(HomeView.class);
	@Inject
	private CoinHeaderRepository coinHeaderRepository;
	private List<CoinHeader> coinHeaderList;
	private String html;

	@PostConstruct
	public void init() {
		log.info("##### HomeView #####");
		try {
			coinHeaderList = coinHeaderRepository.findAll();
			log.info("coinHeaderList.size : "+coinHeaderList.size());
			html = "";
			html += "<div class=\"card\">";
			html += "<div class=\"card-header\">";
			html += "<h3 class=\"card-title\">Masternode</h3>";
			html += "</div>";
			html += "<div class=\"card-body\">";
			html += "<table id=\"example1\" class=\"table table-bordered table-striped\">";
			html += "<thead>";
			html += "<tr>";
			html += "<th>Coin / detail</th>";
			html += "<th>Price</th>";
			html += "<th>Change</th>";
			html += "<th>Valume</th>";
			html += "<th>Marketcap</th>";
			html += "<th>ROI</th>";
			html += "<th>Nodes</th>";
			html += "<th>Required</th>";
			html += "<th>Mn worth</th>";
			html += "</tr>";
			html += "</thead>";
			html += "<tbody>";
			for(CoinHeader coinHeader : coinHeaderList) {
				html += "<tr>";
				html += "<th>"+coinHeader.getCoinName()+" ("+coinHeader.getCoinAbbrivation()+")</th>";
				html += "<th>"+coinHeader.getPrice()+"</th>";
				html += "<th>"+coinHeader.getPriceChange()+"</th>";
				html += "<th>"+coinHeader.getValume()+"</th>";
				html += "<th>"+coinHeader.getMarketcap()+"</th>";
				html += "<th>"+coinHeader.getRoi()+"</th>";
				html += "<th>"+coinHeader.getNodesNumber()+"</th>";
				html += "<th>"+coinHeader.getRequired()+"</th>";
				html += "<th>"+coinHeader.getMnWorth()+"</th>";
				html += "</tr>";
			}
			html += "</tbody>";
			html += "<tfoot>";
			html += "<tr>";
			html += "<th>Coin / detail</th>";
			html += "<th>Price</th>";
			html += "<th>Change</th>";
			html += "<th>Valume</th>";
			html += "<th>Marketcap</th>";
			html += "<th>ROI</th>";
			html += "<th>Nodes</th>";
			html += "<th>Required</th>";
			html += "<th>Mn worth</th>";
			html += "</tr>";
			html += "</tfoot>";
			html += "</table>";
			html += "</div>";
			html += "</div>";
			
			log.info(html);
			
//			<div class="card">
//            <div class="card-header">
//              <h3 class="card-title">Masternode</h3>
//            </div>
//            <div class="card-body">
//              <table id="example1" class="table table-bordered table-striped">
//                <thead>
//                <tr>
//                  <th>Rendering engine</th>
//                  <th>Browser</th>
//                  <th>Platform(s)</th>
//                  <th>Engine version</th>
//                  <th>CSS grade</th>
//                </tr>
//                </thead>
//                <tbody>
//                <tr>
//                  <td>Trident</td>
//                  <td>Internet
//                    Explorer 4.0
//                  </td>
//                  <td>Win 95+</td>
//                  <td> 4</td>
//                  <td>X</td>
//                </tr>
//                </tbody>
//                <tfoot>
//                <tr>
//                  <th>Rendering engine</th>
//                  <th>Browser</th>
//                  <th>Platform(s)</th>
//                  <th>Engine version</th>
//                  <th>CSS grade</th>
//                </tr>
//                </tfoot>
//              </table>
//            </div>
//          </div>
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}
