package com.group.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
@Configuration
@PropertySource("classpath:application.properties")
@Component
public class FourSquareConnConfig {
	@Value("${client.id}")
	private String clientId=null;
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	@Value("${client.secret}")
	private String clientSecret=null;
	
}
