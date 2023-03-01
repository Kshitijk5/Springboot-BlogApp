package com.example.demo.payload;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

//POST DTO

public class PostDto {
	private long id;

	@NotEmpty
	@Size(min = 3, message = "Title must be of 3-6 characters")
	private String title;
	@NotEmpty
	private String description;
	@NotEmpty
	private String content;
	private Set<CommentDto> comments;

	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PostDto() {
		// default
	}

}
