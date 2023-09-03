package com.doburn.memoit.user;

import static com.doburn.memoit.user.Status.*;

import java.util.ArrayList;
import java.util.List;

import com.doburn.memoit.global.BaseEntity;
import com.doburn.memoit.memo.Memo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	@Column(name = "password", nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;

	@Enumerated(EnumType.STRING)
	@Column(name = "platform", nullable = false)
	private Platform platform;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;

	@OneToMany(mappedBy = "user")
	private final List<Memo> memos = new ArrayList<>();

	public User(String email, String password, Platform platform, Role role) {
		this.email = email;
		this.password = password;
		this.platform = platform;
		this.role = role;

		this.status = ACTIVE;
	}

	private void resign() {
		this.status = RESIGN;
	}

	private void activate() {
		this.status = ACTIVE;
	}

	public void addMemo(Memo memo) {
		this.memos.add(memo);
	}
}
