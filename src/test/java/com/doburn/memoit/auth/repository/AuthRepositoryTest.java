package com.doburn.memoit.auth.repository;

import static com.doburn.memoit.common.fixtures.AuthFixtures.*;
import static com.doburn.memoit.common.fixtures.UserFixtures.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

import com.doburn.memoit.auth.AuthToken;
import com.doburn.memoit.user.User;
import com.doburn.memoit.user.repository.UserRepository;

@DataJpaTest
@EnableJpaAuditing
@ActiveProfiles("test")
class AuthRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthRepository authRepository;

	@DisplayName("해당 유저ID의 토큰이 존재하면 true를 반환한다.")
	@Test
	void 해당_유저ID의_토큰이_존재하면_true를_반환한다() {
		// given
		User user = userRepository.save(준형());
		authRepository.save(new AuthToken(user, REFRESH_TOKEN));

		// when
		boolean result = authRepository.existsByUserId(user.getId());

		// then
		assertThat(result).isTrue();
	}

	@DisplayName("해당 유저ID의 토큰이 존재하지 않으면 false를 반환한다.")
	@Test
	void 해당_유저ID의_토큰이_존재하지않으면_false를_반환한다() {
		// given & when
		boolean result = authRepository.existsByUserId(0L);

		// then
		assertThat(result).isFalse();
	}

	@DisplayName("해당 유저ID의 토큰이 존재하면 토큰값을 가져온다.")
	@Test
	void 해당_유저ID의_토큰이_존재하면_토큰값을_가져온다() {
		// given
		User user = userRepository.save(준형());
		authRepository.save(new AuthToken(user, REFRESH_TOKEN));

		// when
		AuthToken token = authRepository.getAuthTokenByUserId(user.getId());

		// then
		assertThat(token.getRefreshToken()).isEqualTo(REFRESH_TOKEN);
	}

	@DisplayName("해당 유저ID의 토큰이 존재하지 않으면 예외를 던진다.")
	@Test
	void 해당_유저ID의_토큰이_존재하지_않으면_예외를_던진다() {
		// given & when & then
		assertThatThrownBy(() -> authRepository.getAuthTokenByUserId(0L))
			.isInstanceOf(RuntimeException.class);
	}

}