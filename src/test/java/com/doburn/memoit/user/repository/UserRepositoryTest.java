package com.doburn.memoit.user.repository;

import static com.doburn.memoit.common.fixtures.UserFixtures.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

import com.doburn.memoit.user.User;

@DataJpaTest
@EnableJpaAuditing
@ActiveProfiles("test")
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@DisplayName("중복된 이메일이 존재하면 true를 반환한다.")
	@Test
	void 중복된_이메일이_존재하면_true를_반환한다() {
		// given
		userRepository.save(세욱());

		// when & then
		assertThat(userRepository.existsByEmail(세욱_이메일)).isTrue();
	}

	@DisplayName("이메일로 회원을 찾는다.")
	@Test
	void 이메일로_회원을_찾는다() {
		// given
		User savedUser = userRepository.save(세욱());

		// when
		User foundUser = userRepository.getUserByEmail(세욱_이메일);

		// then
		assertThat(savedUser.getId()).isEqualTo(foundUser.getId());
	}

	@DisplayName("존재하지 않는 email로 조회한 경우 예외를 던진다.")
	@Test
	void 존재하지_않는_email로_조회한_경우_예외를_던진다() {
		// given & when & then
		assertThatThrownBy(() -> userRepository.getUserByEmail(준형_이메일))
			.isInstanceOf(RuntimeException.class);
	}

	@DisplayName("존재하지 않는 id면 예외를 던진다.")
	@Test
	void 존재하지_않는_id면_예외를_던진다() {
		// given
		Long id = 0L;

		// when & then
		assertThatThrownBy(() -> userRepository.validateExistsById(id))
			.isInstanceOf(RuntimeException.class);
	}

}