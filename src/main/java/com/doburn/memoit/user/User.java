package com.doburn.memoit.user;

import static com.doburn.memoit.user.Status.*;

import com.doburn.memoit.global.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;

	@Enumerated(EnumType.STRING)
	@Column(name = "platform", nullable = false)
	private Platform platform;

	public User(String email, Platform platform) {
		this.email = email;
		this.platform = platform;

		this.status = ACTIVE;
	}

	public void resign() {
		this.status = RESIGN;
	}

	public void activate() {
		this.status = ACTIVE;
	}
}
