package com.jamallo.service;

import com.jamallo.models.Comment;

public interface CommentService {
	
	public Comment createComment (
			Comment comment, 
			Integer postId, 
			Integer userId
			) throws Exception;
	
	public Comment findCommentById (Integer commentId);
	
	public Comment likeComment (Integer CommentId, Integer userId); 

}
