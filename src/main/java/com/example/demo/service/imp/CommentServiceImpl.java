package com.example.demo.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.CommentDto;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		// checking if posts exists or not
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		// converting DTO to entity
		Comment comment = mapToEntity(commentDto);
		comment.setPost(post);

		// saving
		Comment createdComment = commentRepository.save(comment);

		return mapToDTO(createdComment);

	}

	private Comment mapToEntity(CommentDto commentDto) {
		// WITH MODEL MAPPER
		Comment comment = mapper.map(commentDto, Comment.class);

		// WITHOUT MODELMAPPER
//		Comment comment = new Comment();
//		comment.setName(commentDto.getName());
//		comment.setEmail(commentDto.getEmail());
//		comment.setBody(commentDto.getBody());

		return comment;
	}

	private CommentDto mapToDTO(Comment comment) {
		// WITH MODELMAPPER
		CommentDto commentDto = mapper.map(comment, CommentDto.class);
		// WIHTOUT MODELMAPPER
//		CommentDto commentDto = new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
//		commentDto.setBody(comment.getBody());

		return commentDto;
	}

	@Override
	public CommentDto getComment(long postId, long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		return mapToDTO(comment);
	}

	@Override
	public List<CommentDto> getAllCommentsByPostId(long id) {

		List<Comment> comments = commentRepository.findByPostId(id);

		List<CommentDto> commentDto = new ArrayList<>();

		for (Comment comment : comments) {

			commentDto.add(mapToDTO(comment));
		}

		return commentDto;

	}

	@Override
	public CommentDto updateCommentById(long id, CommentDto commentDto) {

		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		// saving
		Comment updatedComment = commentRepository.save(comment);

		return mapToDTO(updatedComment);

	}

	@Override
	public String deleteCommentById(long id) {
		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
		commentRepository.delete(comment);
		return "Comment with ID-" + id + " deleted";

	}

}
