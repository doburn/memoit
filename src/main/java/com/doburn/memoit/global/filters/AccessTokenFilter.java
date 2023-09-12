package com.doburn.memoit.global.filters;

import java.io.IOException;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.doburn.memoit.auth.TokenValidator;
import com.doburn.memoit.global.threadlocals.ThreadLocalValueHolder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccessTokenFilter extends OncePerRequestFilter {

	private final TokenValidator tokenValidator;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String authorization = request.getHeader("Authorization");

		if (!StringUtils.hasText(authorization)) {
			throw new RuntimeException("권한이 없습니다.");
		}

		String token = authorization.substring(7); // Bearer

		// 1. access token이 유효하지 않은 경우 -> 클라이언트는 로그인 페이지로 리다이렉트 시킴
		// 2. access token이 만료된 경우 -> 클라이언트는 refresh token으로 access token 재발급을 요청함
		try {
			Long userId = tokenValidator.validateToken(token);
			ThreadLocalValueHolder.setThreadLocalValue(userId); // 스레드 로컬에 유저id 값 설정

			filterChain.doFilter(request, response); // 다음 필터 or 서블릿으로 진행
		} finally {
			ThreadLocalValueHolder.removeThreadLocalValue(); // 스레드 로컬을 비운다
		}

	}

	/**
	 * /oauth 로 시작하는 요청은 거른다
	 */
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return request.getServletPath().startsWith("/oauth");
	}

}
