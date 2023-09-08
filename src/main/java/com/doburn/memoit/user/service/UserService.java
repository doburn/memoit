package com.doburn.memoit.user.service;

import static com.doburn.memoit.user.Status.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doburn.memoit.user.User;
import com.doburn.memoit.user.dto.request.UserSignUpRequest;
import com.doburn.memoit.user.dto.response.UserResponse;
import com.doburn.memoit.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public UserResponse getUserInfo(Long id) {
		User user = userRepository.getReferenceById(id);
		return new UserResponse(user);
	}

	@Transactional
	public UserResponse signUp(UserSignUpRequest request) {
		User signUpUser = new User(request.getEmail(), request.getPlatform());
		User savedUser = userRepository.save(signUpUser);
		return new UserResponse(savedUser);
	}

	@Transactional
	public void changeStatus(Long id) {
		User user = userRepository.getReferenceById(id);

		if (user.getStatus() == ACTIVE)
			user.resign();
		else
			user.activate();
	}

}
