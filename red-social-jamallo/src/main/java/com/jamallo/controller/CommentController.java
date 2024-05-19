package com.jamallo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jamallo.models.Comment;
import com.jamallo.models.User;
import com.jamallo.service.CommentService;
import com.jamallo.service.UserService;

// Controlador REST para manejar las operaciones relacionadas con los comentarios, 
// como la creación de nuevos comentarios y la acción de dar 'like' a comentarios.
@RestController
public class CommentController {

	// Servicio para manejar la lógica de negocio relacionada con los comentarios.
	@Autowired
	private CommentService commentService;
	
	// Servicio para manejar la lógica de negocio relacionada con los usuarios.
	@Autowired
	private UserService userService;
	
	/**
     * Endpoint para crear un nuevo comentario en una publicación.
     * 
     * @param comment -> Recibe el comentario a crear.
     * @param jwt -> Recibe el token JWT del usuario que realiza la solicitud.
     * @param postId -> Recibe el ID de la publicación donde se creará el comentario como un parámetro de la URL.
     * @return El comentario creado.
     * @throws Exception -> Si ocurre algún error durante la creación del comentario.
     */
	@PostMapping("/api/comments/post/{postId}")
	public Comment createComment(@RequestBody Comment comment, 
			@RequestHeader("Authorization") String jwt,
			@PathVariable("postId") Integer postId
			) throws Exception {
		
		// Encuentra el usuario a partir del JWT
		User user = userService.findUserByJwt(jwt);
		
		// Crea el comentario utilizando el servicio de comentarios
		Comment createdComment = commentService.createComment(comment, 
				postId, 
				user.getId()
				);
		
		return createdComment;
	}
	
	/**
     * Endpoint para dar 'like' a un comentario.
     * 
     * @param jwt -> Recibe el token JWT del usuario que realiza la solicitud.
     * @param commentId -> Recibe el ID del comentario al que se le dará 'like' como un parámetro de la URL.
     * @return El comentario con el 'like' agregado.
     * @throws Exception -> Si ocurre algún error durante el proceso de dar 'like'.
     */
	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment(@RequestHeader("Authorization") String jwt,
			@PathVariable Integer commentId
			) throws Exception {
		
		// Encuentra el usuario a partir del JWT
		User user = userService.findUserByJwt(jwt);
		
		// Da 'like' al comentario utilizando el servicio de comentarios
		Comment likeComment = commentService.likeComment(commentId, user.getId());
		
		return likeComment;
	}
}
