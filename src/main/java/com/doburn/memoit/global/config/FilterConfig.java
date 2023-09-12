package com.doburn.memoit.global.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.doburn.memoit.auth.TokenValidator;
import com.doburn.memoit.global.filters.AccessTokenFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

	private final TokenValidator tokenValidator;

	@Bean
	public FilterRegistrationBean<AccessTokenFilter> accessTokenFilter() {
		FilterRegistrationBean<AccessTokenFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AccessTokenFilter(tokenValidator));
		registrationBean.addUrlPatterns("/*"); // 기본적으로 모든 URL에 대해 적용
		registrationBean.setOrder(1);
		return registrationBean;
	}

}
