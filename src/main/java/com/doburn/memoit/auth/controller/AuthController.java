package com.doburn.memoit.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.doburn.memoit.auth.controller.dto.OAuthUriResponse;
import com.doburn.memoit.global.uri.UriGenerator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final UriGenerator uriGenerator;

	/**
	 * 클라이언트에게 Google loginPage Uri 를 반환한다.
	 */
	@GetMapping("/login")
	public ResponseEntity<OAuthUriResponse> generateOAuthUri() {

		OAuthUriResponse oAuthUriResponse = new OAuthUriResponse(uriGenerator.generateOAuthUri());

		return ResponseEntity.ok(oAuthUriResponse);
	}

}
