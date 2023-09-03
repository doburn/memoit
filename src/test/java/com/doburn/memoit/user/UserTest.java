package com.doburn.memoit.user;

import static com.doburn.memoit.common.fixtures.UserFixtures.*;
import static com.doburn.memoit.user.Platform.*;
import static com.doburn.memoit.user.Role.*;
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



}