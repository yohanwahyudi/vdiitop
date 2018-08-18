package org.vdi.core.component.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vdi.core.config.ApplicationProperties;

@Component("httpClientRequest")
public class HttpClientRequest {

	private final Logger logger = LogManager.getLogger(HttpClientRequest.class);
	
	private int httpTimeout;
	private int httpMaxxPool;
	private int httpMaxPerRoute;
	private String httpProxyHost;
	private Integer httpProxyPort;
	private String httpProxyScheme;
	
	private String cookies;
	
	public HttpClientRequest() {
		
	}
	
	@Autowired
	public HttpClientRequest(ApplicationProperties applicationProperties) {
		this.httpTimeout = applicationProperties.getHttpTimeout();
		this.httpMaxxPool = applicationProperties.getHttpMaxPool();
		this.httpMaxPerRoute =applicationProperties.getHttpMaxPerRoute();
		
		this.httpProxyHost = applicationProperties.getHttpProxyHost();
		this.httpProxyPort = applicationProperties.getHttpProxyPort();
		this.httpProxyScheme = applicationProperties.getHttpProxyScheme();
	}
	
	public HttpClient clientInit() {
		
		// set param
		RequestConfig.Builder builder = RequestConfig.custom().setConnectTimeout(httpTimeout * 1000)
				.setConnectionRequestTimeout(httpTimeout * 1000).setSocketTimeout(httpTimeout * 1000);
		
		HttpHost proxy;
		if(httpProxyHost!=null && httpProxyPort!=null && httpProxyScheme!=null) {
			logger.debug("Using Proxy host:"+httpProxyHost+" port:"+httpProxyPort+" scheme:"+httpProxyScheme);
			proxy = new HttpHost(httpProxyHost, httpProxyPort, httpProxyScheme);
			builder.setProxy(proxy);
		}
		
		PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();
		pool.setMaxTotal(httpMaxxPool);
		pool.setDefaultMaxPerRoute(httpMaxPerRoute);

		// http builder
		HttpClientBuilder client = HttpClientBuilder.create();
		client.setDefaultRequestConfig(builder.build());
		client.setConnectionManager(pool);

		return client.build();

	}
	
	public String readUrl(String url) {
		// init
		HttpClient client = clientInit();
		HttpGet get = new HttpGet(url);
		StringBuilder sBuilder = new StringBuilder();

		try {
			HttpResponse resp = client.execute(get);
			logger.debug("response code : " + resp.getStatusLine().getStatusCode());

			BufferedReader reader = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
			String line = "";

			while ((line = reader.readLine()) != null) {
				sBuilder.append(line);
			}

		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sBuilder.toString();
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

}
