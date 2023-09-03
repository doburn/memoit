package com.doburn.memoit.common.fixtures;

import static com.doburn.memoit.user.Platform.*;
import static com.doburn.memoit.user.Role.*;

import com.doburn.memoit.user.User;

public class UserFixtures {

	/* 관리자 */
	public static final String 관리자_이메일 = "admin@mail.com";
	public static final String 관리자_비밀번호 = "admin12345";

	/* 지호 */
	public static final String 지호_이메일 = "jiho@mail.com";
	public static final String 지호_비밀번호 = "jiho12345";

	/* 준형 */
	public static final String 준형_이메일 = "joonhyung@mail.com";
	public static final String 준형_비밀번호 = "joonhyung12345";

	/* 세욱-구글 */
	public static final String 세욱_이메일 = "sergei@gmail.com";
	public static final String 세욱_비밀번호 = "다른_플랫폼";

	public static User 관리자() {
		return new User(관리자_이메일, 관리자_비밀번호, MEMOIT, ADMIN);
	}

	public static User 지호() {
		return new User(지호_이메일, 지호_비밀번호, MEMOIT, USER);
	}

	public static User 준형() {
		return new User(준형_이메일, 준형_비밀번호, MEMOIT, USER);
	}

	public static User 세욱() {
		return new User(세욱_이메일, 세욱_비밀번호, GOOGLE, USER);
	}

}
