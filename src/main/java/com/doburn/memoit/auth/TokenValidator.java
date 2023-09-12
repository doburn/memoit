package com.doburn.memoit.auth;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenValidator {

	private final SecretKey key;

	public TokenValidator(@Value("${security.jwt.secretKey}") String secretKey) {
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}

	public Long validateToken(String token) {
		Jws<Claims> claims;

		try {
			claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token);
		} catch (JwtException | IllegalArgumentException e) {
			throw new RuntimeException("액세스 토큰 에러");
		}

		boolean isTokenExpired = claims.getBody()
			.getExpiration()
			.before(new Date());

		if (isTokenExpired)
			throw new RuntimeException("액세스 토큰 만료");

		return Long.parseLong(claims
			.getBody()
			.getSubject());

	}

}
