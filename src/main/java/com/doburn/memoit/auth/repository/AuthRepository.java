package com.doburn.memoit.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doburn.memoit.auth.AuthToken;

public interface AuthRepository extends JpaRepository<AuthToken, Long> {


}
