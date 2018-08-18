package org.vdi.core.component.itop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vdi.core.component.http.HttpClientRequest;
import org.vdi.core.config.ApplicationProperties;
import org.vdi.core.constant.ConstantProperties;

@Component("itopHttpConnection")
public class ItopHttpConnection {
	
	Logger logger = LogManager.getLogger(ItopHttpConnection.class);
	
	@Autowired
	ApplicationProperties properties;
	
	@Autowired
	HttpClientRequest clientRequest;
	
	public ItopHttpConnection() {
		CookieHandler.setDefault(new CookieManager());
	}
	
	public String getQueryPhrasebookPageContent(String url) throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(url);
		
		// set header 
		get.setHeader(ConstantProperties.ITOP_WEB_HEADER_ACCEPT, ConstantProperties.ITOP_WEB_HEADER_ACCEPT_VAL);
		get.setHeader(ConstantProperties.ITOP_WEB_HEADER_ACCEPT_ENCODING, ConstantProperties.ITOP_WEB_HEADER_ACCEPT_ENCODING_VAL);
		get.setHeader(ConstantProperties.ITOP_WEB_HEADER_ACCEPT_LANGUAGE, ConstantProperties.ITOP_WEB_HEADER_ACCEPT_LANGUAGE_VAL);
		get.setHeader(ConstantProperties.ITOP_WEB_HEADER_AUTHORIZATION, properties.getItopWebAuthorization());
		get.setHeader(ConstantProperties.ITOP_WEB_HEADER_CACHE_CONTROL, ConstantProperties.ITOP_WEB_HEADER_ACCEPT_CACHE_CONTROL_VAL);
		get.setHeader(ConstantProperties.ITOP_WEB_HEADER_HOST, properties.getItopWebHost());
		get.setHeader(ConstantProperties.ITOP_WEB_HEADER_PROXY_CONNECTION, ConstantProperties.ITOP_WEB_HEADER_ACCEPT_PROXY_CONNECTION_VAL);
		get.setHeader(ConstantProperties.ITOP_WEB_HEADER_UPGRADE_INSECURE_REQUESTS, ConstantProperties.ITOP_WEB_HEADER_ACCEPT_UPGRADE_INSECURE_REQUESTS_VAL);
		get.setHeader(ConstantProperties.ITOP_WEB_HEADER_USER_AGENT, ConstantProperties.ITOP_WEB_HEADER_USER_AGENT_VAL);
		
		HttpClient client = clientRequest.clientInit();
		HttpResponse response = client.execute(get);
		
		String responseStatusLine = response.getStatusLine().toString();
		logger.debug("\nSending 'GET' request to URL : " + url);
		logger.debug("Response Line : " + responseStatusLine);

		// set cookies
		clientRequest.setCookies(response.getFirstHeader(ConstantProperties.ITOP_WEB_HEADER_SET_COOKIE) == null ? 
								"" : response.getFirstHeader(ConstantProperties.ITOP_WEB_HEADER_SET_COOKIE).toString());
		
		logger.debug("cookie: "+clientRequest.getCookies());
		
		InputStreamReader inputStreamReader = new InputStreamReader(response.getEntity().getContent());
		BufferedReader rd = new BufferedReader(inputStreamReader);

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		try {
			inputStreamReader.close();
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result.toString();
		
	}

}
