package com.example.demo.controller;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.PostDto;
import com.example.demo.payload.PostResponse;
import com.example.demo.service.PostService;
import com.example.demo.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	// create post
	@PostMapping
	public ResponseEntity<PostDto> createPost( @Valid @RequestBody PostDto dto) {
		return new ResponseEntity<PostDto>(postService.createPost(dto), HttpStatus.CREATED);
	}

	// list all posts
	@GetMapping
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
			) {
		return new ResponseEntity<PostResponse>(postService.getAllPosts(pageNo, pageSize,sortBy,sortDir), HttpStatus.OK);
	}

	// get post by id

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getById(@PathVariable("id") long id) {
		return ResponseEntity.ok(postService.getById(id));
	}

	// Update posts

	@PutMapping
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto) {
		return new ResponseEntity<PostDto>(postService.updatePost(postDto), HttpStatus.OK);
	}

	// Delete POST
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable("id") long id) {
		postService.deletePost(id);
		return new ResponseEntity<>("Post with ID-" + id + " Deleted", HttpStatus.OK);
	}

}
