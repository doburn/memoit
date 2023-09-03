package com.doburn.memoit.memo;

import com.doburn.memoit.global.BaseEntity;
import com.doburn.memoit.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "memos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Memo extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memo_id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Memo(String title, String content, User user) {
		this.title = title;
		this.content = content;

		this.user = user;
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}

}
