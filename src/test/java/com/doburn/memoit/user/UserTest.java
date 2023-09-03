package com.doburn.memoit.user;

import static com.doburn.memoit.common.fixtures.UserFixtures.*;
import static com.doburn.memoit.user.Platform.*;
import static com.doburn.memoit.user.Role.*;
import static com.doburn.memoit.user.Status.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	@DisplayName("회원을 생성한다.")
	void 회원을_생성한다() {
		assertDoesNotThrow(() -> new User(지호_이메일, 지호_비밀번호, MEMOIT, USER));
	}

	@Test
	@DisplayName("회원상태를 비활성화한다.")
	void 회원상태를_비활성화한다() {
		User user = 지호();

		user.resign();

		assertThat(user.getStatus()).isEqualTo(RESIGN);
	}

	@Test
	@DisplayName("회원상태를 재활성화한다.")
	void 회원상태를_재활성화한다() {
		User user = 지호();

		user.resign();
		user.activate();

		assertThat(user.getStatus()).isEqualTo(ACTIVE);
	}

}