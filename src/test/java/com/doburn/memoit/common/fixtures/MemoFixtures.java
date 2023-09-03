package com.doburn.memoit.common.fixtures;

import static com.doburn.memoit.common.fixtures.UserFixtures.*;

import com.doburn.memoit.memo.Memo;

public class MemoFixtures {

	/* 메모1 */
	public static final String 메모1_제목 = "메모1_제목";
	public static final String 메모1_내용 = "메모1_내용";

	/* 메모2 */
	public static final String 메모2_제목 = "메모2_제목";
	public static final String 메모2_내용 = "메모2_내용";

	/* 메모3 */
	public static final String 메모3_제목 = "메모3_제목";
	public static final String 메모3_내용 = "메모3_내용";

	public static Memo 지호_메모1() {
		return new Memo(메모1_제목, 메모1_내용, 지호());
	}

	public static Memo 준형_메모2() {
		return new Memo(메모2_제목, 메모2_내용, 준형());
	}

}
