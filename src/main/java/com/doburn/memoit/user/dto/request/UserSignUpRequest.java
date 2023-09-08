package com.doburn.memoit.user.dto.request;

import com.doburn.memoit.user.Platform;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequest {

	private String email;
	private Platform platform;

}
