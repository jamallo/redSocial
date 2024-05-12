package com.jamallo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamallo.models.Comment;

public interface CommentRepository extends JpaRepository <Comment, Integer> {

}
