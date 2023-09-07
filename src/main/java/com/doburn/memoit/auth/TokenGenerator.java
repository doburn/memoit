package com.doburn.memoit.auth;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenGenerator {

	private final SecretKey key;
	private final long accessTokenExpireLengthMilliseconds;
	private final long refreshTokenExpireLengthMilliseconds;

	public TokenGenerator(@Value("${security.jwt.secretKey}") String secretKey,
							@Value("${security.jwt.access.expire-length}") long accessTokenExpireLengthMilliseconds,
							@Value("${security.jwt.refresh.expire-length}") long refreshTokenExpireLengthMilliseconds) {
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		this.accessTokenExpireLengthMilliseconds = accessTokenExpireLengthMilliseconds;
		this.refreshTokenExpireLengthMilliseconds = refreshTokenExpireLengthMilliseconds;
	}

	public String generateAccessToken(String payload) {
		return createToken(payload, accessTokenExpireLengthMilliseconds);
	}

	public String generateRefreshToken(String payload) {
		return createToken(payload, refreshTokenExpireLengthMilliseconds);
	}

	private String createToken(String payload, long expireLengthMilliseconds) {
		Date iat = new Date();
		Date exp = new Date(iat.getTime() + expireLengthMilliseconds);

		return Jwts.builder()
			.setSubject(payload)
			.setIssuedAt(iat)
			.setExpiration(exp)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

}
