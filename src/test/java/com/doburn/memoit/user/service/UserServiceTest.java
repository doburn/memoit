package com.doburn.memoit.user.service;

import static com.doburn.memoit.common.fixtures.UserFixtures.*;
import static com.doburn.memoit.user.Platform.*;
import static com.doburn.memoit.user.Status.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

import com.doburn.memoit.user.User;
import com.doburn.memoit.user.dto.request.UserSignUpRequest;
import com.doburn.memoit.user.dto.response.UserResponse;
import com.doburn.memoit.user.repository.UserRepository;

@DataJpaTest
@EnableJpaAuditing
@ActiveProfiles("test")
class UserServiceTest {

	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	public void init() {
		this.userService = new UserService(userRepository);
	}

	@DisplayName("id로 유저 정보를 가져온다.")
	@Test
	void id로_유저_정보를_가져온다() {
		// given
		User savedUser = userRepository.save(지호());

		// when
		UserResponse user = userService.getUserInfo(savedUser.getId());

		// then
		assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
	}

	@DisplayName("유저 정보를 받아 회원가입 시킨다.")
	@Test
	void 유저_정보를_받아_회원가입_시킨다() {
		// given
		UserSignUpRequest request = new UserSignUpRequest(지호_이메일, GOOGLE);

		// when
		UserResponse response = userService.signUp(request);

		// then
		assertThat(request.getEmail()).isEqualTo(response.getEmail());
	}

	@DisplayName("유저 정보를 active에서 resign으로 변경한다.")
	@Test
	void 유저_정보를_active에서_resign으로_변경한다() {
		// given
		User savedUser = userRepository.save(지호());

		// when
		userService.changeStatus(savedUser.getId());

		// then
		User user = userRepository.getUserByEmail(savedUser.getEmail());
		assertThat(user.getStatus()).isEqualTo(RESIGN);
	}

	@DisplayName("유저 정보를 resign에서 active로 변경한다.")
	@Test
	void 유저_정보를_resign에서_active로_변경한다() {
	    // given
		User savedUser = userRepository.save(지호());
		userService.changeStatus(savedUser.getId());
		User resignUser = userRepository.getUserByEmail(savedUser.getEmail());

		// when
		userService.changeStatus(resignUser.getId());
		
		// then
		User user = userRepository.getUserByEmail(resignUser.getEmail());
		assertThat(user.getStatus()).isEqualTo(ACTIVE);
	}
	
}