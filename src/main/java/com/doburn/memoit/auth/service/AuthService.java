package com.doburn.memoit.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doburn.memoit.auth.dto.AuthTokenRequest;
import com.doburn.memoit.auth.dto.AuthTokenResponse;
import com.doburn.memoit.global.properties.GoogleProperties;
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

		return new AuthTokenResponse("액세스 토큰", "리프레시 토큰");
	}

}
