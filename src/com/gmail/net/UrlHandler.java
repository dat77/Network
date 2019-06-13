package com.gmail.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UrlHandler {

	public static String hostAvailable(String siteName) {
		try {
			String hostName = "";
			String fileName = "";
			if (siteName.indexOf('/') == -1) {
				hostName = siteName;
			} else {
				hostName = siteName.substring(0, siteName.indexOf('/'));
				fileName = siteName.substring(siteName.indexOf('/'));
			}
			URL url = new URL("https", hostName, fileName);
			HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
			String result;
			int responseCode = urlc.getResponseCode();
			if (responseCode >= 400) {
				result = siteName + " is inaccessible" + "\n\t" + responseCode + " : " + urlc.getResponseMessage();
			} else if (responseCode < 400 & responseCode >= 100) {
				result = siteName + " is accessible" + "\n\t" + responseCode + " : " + urlc.getResponseMessage();
			} else {
				result = siteName + " accessability is unknown" + "\n\t" + responseCode + " : "
						+ urlc.getResponseMessage();
			}
			return result;
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	public static String[] scrapeLinksFromHtml(String htmlName) {
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(htmlName);
			URLConnection urlc = url.openConnection();
			long l = urlc.getContentLengthLong();
			if (l != 0) {
				BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
				String htmlStr = ""; 
				while((htmlStr = br.readLine()) != null) {
					int startIndex = htmlStr.indexOf("href=");
					if (startIndex != -1) {
					    startIndex = htmlStr.indexOf('"', startIndex)+1;
						int endIndex = htmlStr.indexOf('"', startIndex);
						sb.append(htmlStr.substring(startIndex, endIndex)+"\n");
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return sb.toString().split("\n");
	}
	
}
