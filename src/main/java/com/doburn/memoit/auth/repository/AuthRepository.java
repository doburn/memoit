package com.doburn.memoit.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doburn.memoit.auth.AuthToken;

public interface AuthRepository extends JpaRepository<AuthToken, Long> {

	boolean existsByUserId(Long userId);

	Optional<AuthToken> findAuthTokenByUserId(Long userId);

	default AuthToken getByUserId(Long userId) {
		return findAuthTokenByUserId(userId)
			.orElseThrow(() -> new RuntimeException());
	}

}
