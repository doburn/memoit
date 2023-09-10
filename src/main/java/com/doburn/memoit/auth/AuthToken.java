package com.doburn.memoit.auth;

import org.springframework.util.StringUtils;

import com.doburn.memoit.global.BaseEntity;
import com.doburn.memoit.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_tokens")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AuthToken extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_token_id")
	private Long id;

	@Column(name = "refresh_token", nullable = false)
	private String refreshToken;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public AuthToken(String refreshToken, User user) {
		this.refreshToken = refreshToken;
		this.user = user;
	}

	public void change(String refreshToken) {
		if (StringUtils.hasText(refreshToken)) {
			this.refreshToken = refreshToken;
		}
	}

}
