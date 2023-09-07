package com.doburn.memoit.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.doburn.memoit.auth.dto.AuthTokenResponse;
import com.doburn.memoit.auth.dto.OAuthUriResponse;
import com.doburn.memoit.auth.dto.AuthTokenRequest;
import com.doburn.memoit.auth.service.AuthService;
import com.doburn.memoit.auth.UriGenerator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final UriGenerator uriGenerator;
	private final AuthService authService;

	/**
	 * 클라이언트에게 Google loginPage Uri 를 반환한다.
	 */
	@GetMapping("/login")
	public ResponseEntity<OAuthUriResponse> generateOAuthUri() {

		OAuthUriResponse oAuthUriResponse = new OAuthUriResponse(uriGenerator.generateOAuthUri());

		return ResponseEntity.ok(oAuthUriResponse);
	}

	/**
	 * 클라이언트가 유저 로그인을 확인하고, Authorization code를 전달한다.
	 * 
	 * Authorization code로 Google에서 token을 얻어 사용자에게 인가 정보를 넘겨준다
	 *
	 * 나중에 JSON으로 응답받으면 @RequestBody로 변경
	 */
	@GetMapping("/oauth")
	public ResponseEntity<AuthTokenResponse> generateAccessAndRefreshToken(
		@ModelAttribute AuthTokenRequest authTokenRequest) {
		AuthTokenResponse authTokenResponse =
			authService.generateAccessAndRefreshToken(uriGenerator.generateTokenUri(),
				authTokenRequest);

		return ResponseEntity.ok(authTokenResponse);
	}

}
