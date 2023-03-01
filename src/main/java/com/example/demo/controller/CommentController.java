package com.example.demo.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.CommentDto;
import com.example.demo.service.CommentService;

@RestController
@RequestMapping("/api/posts/{post_id}/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping
	public ResponseEntity<CommentDto> createComment(@PathVariable("post_id") long id,
			 @Valid @RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(commentService.createComment(id, commentDto), HttpStatus.CREATED);
	}

	// getting comment for a post with id post_id through comment with comment id id
	@GetMapping("/{comment_id}")
	public ResponseEntity<CommentDto> getComment(@PathVariable("comment_id") long commentId,
			@PathVariable("post_id") long postId) {
		return new ResponseEntity<CommentDto>(commentService.getComment(postId, commentId), HttpStatus.OK);
	}

	// get all the commments for a post
	@GetMapping
	public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable("post_id") long id) {

		return new ResponseEntity<List<CommentDto>>(commentService.getAllCommentsByPostId(id), HttpStatus.OK);
	}

	// update comment

	@PutMapping("/{comment_id}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable("comment_id") long id,
			@Valid @RequestBody CommentDto commentDto) {
		return new ResponseEntity<CommentDto>(commentService.updateCommentById(id, commentDto), HttpStatus.OK);
	}

	@DeleteMapping("/{comment_id}")
	public ResponseEntity<String> deleteCommentById(@PathVariable("comment_id") long id) {
		return  ResponseEntity.ok(commentService.deleteCommentById(id));
	}

}
