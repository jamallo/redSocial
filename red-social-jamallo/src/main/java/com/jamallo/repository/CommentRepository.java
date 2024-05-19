package com.jamallo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamallo.models.Comment;

/**
 * Repositorio para la entidad Comment.
 * Proporciona métodos CRUD para gestionar comentarios en la base de datos.
 */
public interface CommentRepository extends JpaRepository <Comment, Integer> {

	// Este repositorio hereda los métodos CRUD básicos de JpaRepository.
}
