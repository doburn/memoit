package com.doburn.memoit.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doburn.memoit.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
