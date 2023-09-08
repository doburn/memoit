package com.doburn.memoit.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doburn.memoit.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	Optional<User> findUserByEmail(String email);

	default User getUserByEmail(String email) {
		return findUserByEmail(email)
			.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
	}

}