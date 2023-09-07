package com.doburn.memoit.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import lombok.Getter;


@ConfigurationProperties("oauth.google")
@Getter
public class GoogleProperties {

	private final String clientId;
	private final String clientSecret;
	private final String scope;
	private final String accessType;
	private final String oauthEndPoint;
	private final String tokenEndPoint;
	private final String redirectUri;

	@ConstructorBinding
	public GoogleProperties(String clientId, String clientSecret, String scope, String accessType, String oauthEndPoint,
		String tokenEndPoint, String redirectUri) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.scope = scope;
		this.accessType = accessType;
		this.oauthEndPoint = oauthEndPoint;
		this.tokenEndPoint = tokenEndPoint;
		this.redirectUri = redirectUri;
	}
}
