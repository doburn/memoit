package com.doburn.memoit.user.dto.response;

import com.doburn.memoit.user.Platform;
import com.doburn.memoit.user.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

	private Long id;
	private String email;
	private Status status;
	private Platform platform;

}
