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

// Implementación de la interfaz CommentService para manejar 
// la lógica de negocio relacionada con los comentarios en los posts.
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
	

	/**
     * Crea un nuevo comentario en un post.
     * 
     * @param comment -> Recibe el comentario a crear.
     * @param postId -> Recibe el ID del post en el que se hará el comentario.
     * @param userId -> Recibe el ID del usuario que hace el comentario.
     * @return El comentario creado y guardado.
     * @throws Exception -> Si el usuario o el post no se encuentran.
     */
	@Override
	public Comment createComment(
			Comment comment, 
			Integer postId, 
			Integer userId
			) throws Exception {
		
		// Obtiene el usuario utilizando el ID
		User user = userService.findUserById(userId);
		
		// Obtiene el post utilizando el ID
		Post post = postService.findPostById(postId);
		
		// Asigna el usuario al comentario
		comment.setUser(user);
		// Establece el contenido
		comment.setContent(comment.getContent());
		// Establece la fecha de creación
		comment.setCreateAt(LocalDateTime.now());
		
		// Guarda el post actualizado
		Comment savedComment = commentRepository.save(comment);		
		post.getComments().add(savedComment);		
		postRepository.save(post);
		
		return savedComment;
	}

	/**
     * Encuentra un comentario por su ID.
     * 
     * @param commentId -> Recibe el ID del comentario.
     * @return El comentario correspondiente al ID.
     * @throws Exception -> Si el comentario no se encuentra.
     */
	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		
		// Busca un comentario por su ID
		Optional<Comment> opt = commentRepository.findById(commentId);
		
		// Lanza la excepción si no lo encuentra
		if (opt.isEmpty()) {
			throw new Exception ("El comentario no existe");
		}
		
		return opt.get();
	}

	/**
     * Le da o quita un 'like' a un comentario.
     * 
     * @param commentId -> Recibe el ID del comentario.
     * @param userId -> Recibe el ID del usuario que le da o quita el 'like'.
     * @return El comentario actualizado.
     * @throws Exception -> Si el comentario o el usuario no se encuentran.
     */
	@Override
	public Comment likeComment(Integer CommentId, Integer userId) throws Exception {

		// Busca el comentario por su ID
		Comment comment = findCommentById(CommentId);
		
		// Busca el usuario por su ID
		User user = userService.findUserById(userId);
		
		// Añade o quita el usuario de la lista de 'likes' del comentario
		if (!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		} else comment.getLiked().remove(user);
		
		return commentRepository.save(comment);
	}

}
