package com.doburn.memoit.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter // JSON으로 바뀌면 제거
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenRequest {

	private String code;
	private String scope;
	private String authuser;
	private String prompt;

}
