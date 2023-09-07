package com.doburn.memoit.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GoogleTokenResponse {

	private String token_type;
	private String access_token;
	private String refresh_token;
	private String scope;
	private String id_token;
	private int expires_in;

}
