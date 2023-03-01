package com.example.demo.service;


import com.example.demo.payload.PostDto;
import com.example.demo.payload.PostResponse;

public interface PostService {

	public PostDto createPost(PostDto postDto);

	public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortdir);

	public PostDto getById(long id);

	public PostDto updatePost(PostDto postDto);

	public void deletePost(long id);

}
