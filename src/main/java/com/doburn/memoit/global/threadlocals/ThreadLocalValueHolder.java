package com.doburn.memoit.global.threadlocals;

public class ThreadLocalValueHolder {

	private static final ThreadLocal<Long> threadLocalValue = new ThreadLocal<>();

	public static Long getThreadLocalValue() {
		return threadLocalValue.get();
	}

	public static void setThreadLocalValue(Long value) {
		threadLocalValue.set(value);
	}

	public static void removeThreadLocalValue() {
		threadLocalValue.remove();
	}

}
