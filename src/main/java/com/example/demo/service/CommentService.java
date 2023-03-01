package com.example.demo.service;

import java.util.List;

import com.example.demo.payload.CommentDto;

public interface CommentService {
	public CommentDto createComment(long postId, CommentDto commentDto);

	public CommentDto getComment(long postId, long CommentId);

	public List<CommentDto> getAllCommentsByPostId(long id);
	
	public CommentDto updateCommentById(long id,CommentDto commentDto);
	
	public String deleteCommentById(long id);
}
