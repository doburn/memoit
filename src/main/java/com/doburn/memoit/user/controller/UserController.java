package com.doburn.memoit.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doburn.memoit.user.dto.request.LoginUser;
import com.doburn.memoit.user.dto.response.UserResponse;
import com.doburn.memoit.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	// LoginUser @AuthenticationPrincipal 로 변경
	@GetMapping("/me")
	public ResponseEntity<UserResponse> myinfo(LoginUser loginUser) {
		UserResponse response = userService.getUserInfo(loginUser.getId());
		return ResponseEntity.ok(response);
	}

	// LoginUser @AuthenticationPrincipal 로 변경
	@PatchMapping("/me")
	public ResponseEntity<Void> update(LoginUser loginUser) {
		userService.changeStatus(loginUser.getId());
		return ResponseEntity.noContent().build();
	}

}
