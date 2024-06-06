package com.jamallo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jamallo.models.Post;
import com.jamallo.models.User;
import com.jamallo.response.ApiResponse;
import com.jamallo.service.PostService;
import com.jamallo.service.UserService;

// Controlador REST para manejar las solicitudes relacionadas con los posts.
@RestController
public class PostController {
	
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	/**
     * Endpoint para crear un nuevo post.
     *
     * @param jwt -> Recibe el token JWT del usuario.
     * @param post -> Recibe el objeto Post que se va a crear.
     * @return La respuesta con el post creado. Devuelve el post creado con el estado HTTP ACCEPTED.
     * @throws Exception -> Si ocurre un error durante la creación del post.
     */
	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPost(
			@RequestHeader("Authorization") String jwt, 
			@RequestBody Post post
			) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		Post createdPost = postService.createNewPost(post, reqUser.getId());
		
		return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
	}
	
	/**
     * Endpoint para eliminar un post por su ID.
     *
     * @param postId -> Recibe el ID del post que se va a eliminar.
     * @param jwt -> Recibe el token JWT del usuario.
     * @return La respuesta con el estado de la operación. Devuelve un mensaje de confirmación con el estado HTTP OK.
     * @throws Exception -> Si ocurre un error durante la eliminación del post. 
     */
	@DeleteMapping("/api/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(
			@PathVariable Integer postId, 
			@RequestHeader ("Authorization") String jwt
			) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		String message = postService.deletePost(postId, reqUser.getId());
		ApiResponse res = new ApiResponse(message, true);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}
	
	
	/**
     * Endpoint para obtener un post por su ID.
     *
     * @param postId -> Recibe el ID del post que se va a obtener.
     * @return La respuesta con el post encontrado.
     * @throws Exception Si ocurre un error durante la búsqueda del post.
     */
	@GetMapping("/api/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
		
		
		Post post = postService.findPostById(postId);
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
		
	}
	
	/**
     * Endpoint para obtener todos los posts de un usuario por su ID.
     *
     * @param userId -> Recibe el ID del usuario.
     * @return La respuesta con la lista de posts del usuario.
     */
	@GetMapping("/api/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId) {
		
		List<Post> posts= postService.findPostByUserId(userId);
		
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	/**
     * Endpoint para obtener todos los posts.
     *
     * @return La respuesta con la lista de todos los posts.
     */
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPost() {
		
		List<Post> posts= postService.findAllPost();
		
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	/**
     * Endpoint para guardar un post.
     *
     * @param postId -> Recibe el ID del post que se va a guardar.
     * @param jwt -> Recibe el token JWT del usuario.
     * @return La respuesta con el post guardado.
     * @throws Exception -> Si ocurre un error durante la operación de guardado.
     */
	@PutMapping("/api/posts/save/{postId}")
	public ResponseEntity<Post> savedPostHandler(
			@PathVariable Integer postId, 
			@RequestHeader ("Authorization") String jwt
			) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		Post post = postService.savedPost(postId, reqUser.getId());
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
		
	}
	
	/**
     * Endpoint para dar "me gusta" a un post.
     *
     * @param postId -> Recibe el ID del post que se va a dar "me gusta".
     * @param jwt -> Recibe el token JWT del usuario.
     * @return La respuesta con el post actualizado.
     * @throws Exception -> Si ocurre un error durante la operación de "me gusta".
     */
	@PutMapping("/api/posts/like/{postId}")
	public ResponseEntity<Post> likePostHandler(
			@PathVariable Integer postId, 
			@RequestHeader ("Authorization") String jwt
			) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		Post post = postService.likePost(postId, reqUser.getId());
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
		
	}
	
	

}