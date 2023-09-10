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

import com.doburn.memoit.auth.TokenGenerator;
import com.doburn.memoit.auth.dto.AuthTokenRequest;
import com.doburn.memoit.auth.dto.AuthTokenResponse;
import com.doburn.memoit.auth.dto.GoogleTokenResponse;
import com.doburn.memoit.global.properties.GoogleProperties;
import com.doburn.memoit.user.Platform;
import com.doburn.memoit.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

	private final GoogleProperties googleProperties;
	private final ObjectMapper objectMapper;
	private final TokenGenerator tokenGenerator;

	@Transactional
	public AuthTokenResponse generateAccessAndRefreshToken(String tokenEndpointUri, AuthTokenRequest authTokenRequest) {
		// 1. 사용자가 전해준 code로 google에서 토큰 응답을 받는다
		// 	에러가 나지 않았으면, google 인증 성공
		GoogleTokenResponse googleToken = getGoogleAuthTokenByPost(tokenEndpointUri, authTokenRequest);

		// 2. id_token을 까서 사용자의 이메일을 꺼내온다
		String loginEmail = getEmailFromIdToken(googleToken.getId_token());
		// 2-1. 해당 이메일을 사용하는 사용자가 DB에 존재하는지 체크한다.
		//			만약 사용자가 DB에 없으면, DB에 새로 저장한다.
		// 2-2. 유저 리포지토리에서 가져온 해당 이메일의 유저 객체를 들고있는다.
		User loginUser = new User(loginEmail, "pwd", Platform.GOOGLE, Role.USER);

		// 3. 사용자의 정보(id) 를 이용하여 토큰을 만든다
		// 		리프레시 토큰, 액세스 토큰을 생성한다.
		String refreshToken = tokenGenerator.generateRefreshToken(loginUser.getId().toString());
		String accessToken = tokenGenerator.generateAccessToken(loginUser.getId().toString());

		// 4. auth 테이블에 해당 사용자의 정보(id)를 가진 엔티티가 있으면 refresh token만 업데이트
		//		존재하지 않는 경우 auth 테이블에 사용자 엔티티를 추가해준다


		// 5. 클라이언트에게 리프레시 토큰 + 액세스 토큰을 응답한다.
		return new AuthTokenResponse(accessToken, refreshToken);
	}

	/**
	 * Google Token Response의 ID_TOKEN에서 사용자 식별 정보(이메일)을 받아온다.
	 */
	private String getEmailFromIdToken(String idToken) {
		String[] splitIdToken = idToken.split("\\.");

		byte[] payload = Base64.getDecoder().decode(splitIdToken[1]); // get payload from jwt

		String payloadString = new String(payload);
		String email;

		try {
			Map<String, String> map = objectMapper.readValue(payloadString, Map.class);
			email = map.get("email");
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		if (email == null)
			throw new IllegalStateException("Google 이메일 정보가 없습니다.");

		return email;
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
