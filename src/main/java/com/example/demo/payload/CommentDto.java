package com.example.demo.payload;

import jakarta.validation.constraints.NotEmpty;

public class CommentDto {
	private long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String email;
	@NotEmpty
	private String body;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

//	private Post post
}
