package com.example.demo.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Post;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.PostDto;
import com.example.demo.payload.PostResponse;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private ModelMapper mapper;
	private PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public PostDto createPost(PostDto postDto) {

		// converting DTO to entity
		Post post = mapToEntity(postDto);

		// saving
		Post newPost = postRepository.save(post);

		return mapToDTO(newPost);

	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		// Creating sort object
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		// response list
		List<PostDto> content = new ArrayList<>();

		// page object
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Post> tempPosts = postRepository.findAll(pageable);

		// get all content from page object into list[all posts are put into the
		// listOfPosts]
		List<Post> listOfPosts = tempPosts.getContent();

		for (Post post : listOfPosts) {
			content.add(mapToDTO(post));
		}

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(tempPosts.getNumber());
		postResponse.setPageSize(tempPosts.getSize());
		postResponse.setTotalElements(tempPosts.getTotalElements());
		postResponse.setTotalPages(tempPosts.getTotalPages());
		postResponse.setLast(tempPosts.isLast());

		return postResponse;

	}

	// converting entity to DTO
	private PostDto mapToDTO(Post post) {
		PostDto postDto = mapper.map(post, PostDto.class);

//		PostDto postDto = new PostDto();
//		postDto.setId(post.getId());
//		postDto.setTitle(post.getTitle());
//		postDto.setDescription(post.getDescription());
//		postDto.setContent(post.getContent());

		return postDto;

	}

	// converting DTO to entity

	private Post mapToEntity(PostDto postDto) {
		Post post = mapper.map(postDto, Post.class);
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());

		// modelmap api

		return post;
	}

	@Override
	public PostDto getById(long id) {
		Post tempPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

		return mapToDTO(tempPost);

	}

	@Override
	public PostDto updatePost(PostDto postDto) {
		// check if post exists or not
		Post post = postRepository.findById(postDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postDto.getId()));
		// setting the new values for the post record
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());

		// updating
		Post updatedPost = postRepository.save(post);

		return mapToDTO(updatedPost);
	}

	@Override
	public void deletePost(long id) {
		// check if post exists or not
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		postRepository.deleteById(id);
	}

}
