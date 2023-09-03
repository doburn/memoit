package com.doburn.memoit.memo;

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

@Entity
@Table(name = "memos")
public class Memo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memo_id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

}
