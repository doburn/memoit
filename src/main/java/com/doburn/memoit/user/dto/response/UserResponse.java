package com.doburn.memoit.user.dto.response;

import com.doburn.memoit.user.Platform;
import com.doburn.memoit.user.Status;
import com.doburn.memoit.user.User;

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

	public UserResponse(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.status = user.getStatus();
		this.platform = user.getPlatform();
	}
}
