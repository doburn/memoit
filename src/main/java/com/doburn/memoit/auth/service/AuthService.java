package com.doburn.memoit.auth.service;

import java.util.Base64;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.doburn.memoit.auth.dto.AuthTokenRequest;
import com.doburn.memoit.auth.dto.AuthTokenResponse;
import com.doburn.memoit.auth.dto.GoogleTokenResponse;
import com.doburn.memoit.global.properties.GoogleProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

	private final GoogleProperties googleProperties;
	private final ObjectMapper objectMapper;

	@Transactional
	public AuthTokenResponse generateAccessAndRefreshToken(String tokenEndpointUri, AuthTokenRequest authTokenRequest) {
		// 1. 사용자가 전해준 code로 google에서 토큰 응답을 받는다
		//		에러가 나지 않았으면, google 인증 성공
		GoogleTokenResponse googleToken = getGoogleAuthTokenByPost(tokenEndpointUri, authTokenRequest);

		return new AuthTokenResponse("액세스 토큰", "리프레시 토큰");
	}

	/**
	 * Google Token Endpoint에 POST 요청을 보내서 JSON 토큰을 DTO로 변환해 반환한다.
	 * 에러 발생 시 인증 실패
	 */
	private GoogleTokenResponse getGoogleAuthTokenByPost(String tokenEndpointUri, AuthTokenRequest authTokenRequest) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("code", authTokenRequest.getCode());
		formData.add("client_id", googleProperties.getClientId());
		formData.add("client_secret", googleProperties.getClientSecret());
		formData.add("redirect_uri", googleProperties.getRedirectUri());
		formData.add("grant_type", "authorization_code");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

		ResponseEntity<String> response = restTemplate.exchange(
			tokenEndpointUri,
			HttpMethod.POST,
			request,
			String.class
		);

		try {
			return objectMapper.readValue(response.getBody(), GoogleTokenResponse.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
