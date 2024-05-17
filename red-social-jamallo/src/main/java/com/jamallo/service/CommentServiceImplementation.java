package com.jamallo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamallo.models.Comment;
import com.jamallo.models.Post;
import com.jamallo.models.User;
import com.jamallo.repository.CommentRepository;
import com.jamallo.repository.PostRepository;


@Service
public class CommentServiceImplementation implements CommentService {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	

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
		
		post.getComments().add(savedComment);
		
		postRepository.save(post);
		return savedComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		
		Optional<Comment> opt = commentRepository.findById(commentId);
		
		if (opt.isEmpty()) {
			throw new Exception ("El comentario no existe");
		}
		
		return opt.get();
	}

	@Override
	public Comment likeComment(Integer CommentId, Integer userId) throws Exception {

		Comment comment = findCommentById(CommentId);
		
		User user = userService.findUserById(userId);
		
		if (!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		} else comment.getLiked().remove(user);
		
		return commentRepository.save(comment);
	}

}
