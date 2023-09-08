package com.doburn.memoit.common.fixtures;

import static com.doburn.memoit.user.Platform.*;

import com.doburn.memoit.user.User;

public class UserFixtures {

	/* 지호 */
	public static final String 지호_이메일 = "jiho@mail.com";

	/* 준형 */
	public static final String 준형_이메일 = "joonhyung@mail.com";

	/* 세욱 */
	public static final String 세욱_이메일 = "sergei@gmail.com";

	public static User 지호() {
		return new User(지호_이메일, GOOGLE);
	}

	public static User 준형() {
		return new User(준형_이메일, GOOGLE);
	}

	public static User 세욱() {
		return new User(세욱_이메일, GOOGLE);
	}

}
