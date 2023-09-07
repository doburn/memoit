package com.doburn.memoit.auth;

import org.springframework.stereotype.Component;

import com.doburn.memoit.global.properties.GoogleProperties;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UriGenerator {

	private final GoogleProperties googleProperties;

	public String generateOAuthUri() {
		return googleProperties.getOauthEndPoint()
			+ "?" + "client_id=" + googleProperties.getClientId()
			+ "&" + "redirect_uri=" + googleProperties.getRedirectUri()
			+ "&" + "response_type=code"
			+ "&" + "scope=" + googleProperties.getScope()
			+ "&" + "access_type=" + googleProperties.getAccessType();
	}

	public String generateTokenUri() {
		return googleProperties.getTokenEndPoint();
	}

}
