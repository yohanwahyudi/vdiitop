package org.vdi.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.vdi.core.constant.ConstantProperties;

@Configuration
//@ComponentScan({"org.vdi.core.component.http", "org.vdi.core.config", "org.vdi.core.component.itop","org.vdi.services"})
@ComponentScan({"org.vdi.helper"})
@PropertySources({
	@PropertySource("classpath:application.properties")
})
public class ApplicationProperties {
	
	// HTTP CONFIG
	private final int httpTimeout;
	private final int httpMaxPool;
	private final int httpMaxPerRoute;
	
	private final String httpProxyHost;
	private final Integer httpProxyPort;
	private final String httpProxyScheme;
	
	//http url
	private final String httpURLItopSD;
	public String getHttpURLItopSD() {
		return httpURLItopSD;
	}

	private final String httpURLItopShift1;
	
	//itop web
	private final String itopWebHost;
	private final String itopWebHostUrl;
	private final String itopWebAuthorization;
	
	@Autowired
	public ApplicationProperties(Environment env) {
		
		//http config
		this.httpTimeout = env.getRequiredProperty(ConstantProperties.PROPS_HTTP_TIMEOUT, Integer.class);
		this.httpMaxPool = env.getRequiredProperty(ConstantProperties.PROPS_HTTP_MAXPOOL,	Integer.class);
		this.httpMaxPerRoute = env.getRequiredProperty(ConstantProperties.PROPS_HTTP_MAXPERROUTE, Integer.class);
		
		this.httpProxyHost = env.getProperty(ConstantProperties.PROPS_HTTP_PROXY_HOST, String.class);
		this.httpProxyPort = env.getProperty(ConstantProperties.PROPS_HTTP_PROXY_PORT, Integer.class);
		this.httpProxyScheme = env.getProperty(ConstantProperties.PROPS_HTTP_PROXY_SCHEME, String.class);
		
		//http url
		this.httpURLItopSD = env.getRequiredProperty(ConstantProperties.PROPS_HTTP_URL_ITOP_SD, String.class);
		this.httpURLItopShift1 = env.getRequiredProperty(ConstantProperties.PROPS_HTTP_URL_ITOP_SHIFT1, String.class);
		
		//itop web
		this.itopWebHost = env.getRequiredProperty(ConstantProperties.PROPS_ITOP_WEB_HOST, String.class);
		this.itopWebHostUrl = env.getRequiredProperty(ConstantProperties.PROPS_ITOP_WEB_HOST_URL, String.class);
		this.itopWebAuthorization = env.getRequiredProperty(ConstantProperties.PROPS_ITOP_WEB_AUTHORIZATION, String.class);
		
	}

	public int getHttpTimeout() {
		return httpTimeout;
	}

	public int getHttpMaxPool() {
		return httpMaxPool;
	}

	public int getHttpMaxPerRoute() {
		return httpMaxPerRoute;
	}

	public String getHttpURLItopShift1() {
		return httpURLItopShift1;
	}

	public String getHttpProxyHost() {
		return httpProxyHost;
	}

	public Integer getHttpProxyPort() {
		return httpProxyPort;
	}

	public String getHttpProxyScheme() {
		return httpProxyScheme;
	}

	public String getItopWebHost() {
		return itopWebHost;
	}

	public String getItopWebHostUrl() {
		return itopWebHostUrl;
	}

	public String getItopWebAuthorization() {
		return itopWebAuthorization;
	}

}
