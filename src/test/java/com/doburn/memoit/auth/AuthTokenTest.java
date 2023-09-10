package com.doburn.memoit.auth;

import static com.doburn.memoit.common.fixtures.UserFixtures.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.doburn.memoit.user.User;

class AuthTokenTest {

	@DisplayName("AuthToken을 생성한다.")
	@Test
	void AuthToken을_생성한다() {
		// given
		User user = 지호();
		String refreshToken = "refreshrefreshrefreshrefresh";

		// when & then
		assertDoesNotThrow(() -> new AuthToken(user, refreshToken));
	}

	@DisplayName("AuthToken의 refreshToken값을 변경한다.")
	@Test
	void AuthToken의_refreshToken값을_변경한다() {
		// given
		User user = 지호();
		String refreshToken = "refreshrefreshrefreshrefresh";
		AuthToken authToken = new AuthToken(user, refreshToken);

		String newToken = "newnewnewnewnewnewnewnewnew";

		// when
		authToken.change(newToken);

		// then
		assertThat(authToken.getRefreshToken()).isEqualTo(newToken);
	}

}