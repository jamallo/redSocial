package com.jamallo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.jamallo.models.Comment;
import com.jamallo.models.Post;
import com.jamallo.models.User;
import com.jamallo.repository.CommentRepository;

public class CommentServiceImplementation implements CommentService {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	

	@Override
	public Comment createComment(
			Comment comment, 
			Integer postId, 
			Integer userId
			) throws Exception {
		
		User user = userService.findUserById(userId);
		
		Post post = postService.findPostById(postId);
		
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreateAt(LocalDateTime.now());
		
		Comment savedComment = commentRepository.save(comment);
		
		
		return null;
	}

	@Override
	public Comment findCommentById(Integer commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment likeComment(Integer CommentId, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
