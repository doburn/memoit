package com.doburn.memoit.user.repository;

import static com.doburn.memoit.user.Status.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doburn.memoit.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	Optional<User> findUserByEmail(String email);

	default User getUserByEmail(String email) {
		return findUserByEmail(email)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
	}

	default void validateExistsById(Long id) {
		if(!existsById(id)) {
			throw new RuntimeException("존재하지 않는 회원입니다.");
		}
	}

	default void validateStatusById(Long id) {
		User user = findById(id)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

		if(user.getStatus() != ACTIVE) {
			throw new RuntimeException("비 활성화 회원입니다.");
		}
	}

}
