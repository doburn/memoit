package com.doburn.memoit.memo;

import static com.doburn.memoit.common.fixtures.MemoFixtures.*;
import static com.doburn.memoit.common.fixtures.UserFixtures.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.doburn.memoit.user.User;

class MemoTest {

	@Test
	@DisplayName("메모를 생성한다.")
	void 메모를_생성한다() {
		User user = 지호();

		assertDoesNotThrow(() -> new Memo(메모1_제목, 메모1_내용, user));
	}

	@Test
	@DisplayName("메모 제목과 내용을 업데이트한다.")
	void 메모_제목과_내용을_업데이트한다() {
		User user = 지호();
		Memo memo = new Memo(메모1_제목, 메모1_내용, user);

		memo.update(메모2_제목, 메모2_내용);

		assertThat(memo.getTitle()).isEqualTo(메모2_제목);
		assertThat(memo.getContent()).isEqualTo(메모2_내용);
	}

}