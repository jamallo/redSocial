package com.jamallo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamallo.models.Post;
import com.jamallo.models.User;
import com.jamallo.repository.PostRepository;
import com.jamallo.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	/**
     * Crea y guarda un nuevo post en la base de datos.
     *
     * @param post -> Recibe el post a crear.
     * @param userId -> Recibe el ID del usuario que crea el post.
     * @return El post creado y guardado.
     * @throws Exception -> Si el usuario no se encuentra o ocurre algún otro error.
     */
	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		
		User user = userService.findUserById(userId); // Busca el usuario por su ID utilizando el servicio de usuarios.
		
		Post newPost= new Post(); // Crea una nueva instancia de Post y configura sus propiedades.
		newPost.setTitulo(post.getTitulo()); // Establece el título del post a partir de la solicitud.
		newPost.setImagen(post.getImagen()); // Establece la imagen del post a partir de la solicitud.
		newPost.setCreatedAt(LocalDateTime.now()); // Establece el tiempo de creación del post a la hora actual.
		newPost.setVideo(post.getVideo()); // Establece el video del post a partir de la solicitud.
		newPost.setUser(user); // Asocia el post con el usuario que lo crea.
		
		return postRepository.save(newPost);
	}

	/**
     * Elimina un post de la base de datos.
     *
     * @param postId -> Recibe el ID del post a eliminar.
     * @param userId -> Recibe el ID del usuario que intenta eliminar el post.
     * @return Un mensaje indicando que el post fue eliminado.
     * @throws Exception -> Si el post no se encuentra, el usuario no se encuentra, o el usuario no tiene permiso para eliminar el post.
     */
	@Override
	public String deletePost(
			Integer postId, 
			Integer userId
			) throws Exception {
		
		Post post = findPostById(postId); // Busca el post por su ID utilizando el método findPostById.
		
		User user = userService.findUserById(userId); // Busca el usuario por su ID utilizando el servicio de usuarios.
		
		// Verifica si el usuario que intenta eliminar el post es el propietario del post.
		if(post.getUser().getId()!=user.getId()) {
			throw new Exception("Tu no puedes eliminar post de otros usuarios");
		}
		
		postRepository.delete(post); // Elimina el post del repositorio.
		
		return "post eliminado correctamente";
	}

	/**
     * Encuentra todos los posts creados por un usuario específico.
     *
     * @param userId -> Recibe el ID del usuario cuyos posts se desean recuperar.
     * @return La lista de posts creados por el usuario.
     */
	@Override
	public List<Post> findPostByUserId(Integer userId) {
		return postRepository.findPostByUserId(userId);
	}

	/**
     * Encuentra un post por su ID.
     *
     * @param postId -> Recibe el ID del post a buscar.
     * @return El post encontrado.
     * @throws Exception -> Si el post no se encuentra.
     */
	@Override
	public Post findPostById(Integer postId) throws Exception {
		
		// Busca el post por su ID utilizando el repositorio de posts.
		Optional<Post> opt = postRepository.findById(postId);
		
		// Verifica si el post no se encuentra.
		if(opt.isEmpty()) {
			throw new Exception("post no encontrado con id" + postId);
		}
		return opt.get();
	}

	/**
     * Encuentra todos los posts.
     *
     * @return La lista de todos los posts.
     */
	@Override
	public List<Post> findAllPost() {
		return postRepository.findAll();
	}

	/**
     * Guarda o elimina un post de la lista de posts guardados de un usuario.
     *
     * @param postId -> Recibe el ID del post a guardar o eliminar.
     * @param userId -> Recibe el ID del usuario que guarda o elimina el post.
     * @return El post guardado o eliminado.
     * @throws Exception Si el post o el usuario no se encuentra.
     */
	@Override
	public Post savedPost(
			Integer postId, 
			Integer userId
			) throws Exception {
		
		Post post = findPostById(postId); // Busca el post por su ID utilizando el método findPostById.
		
		User user = userService.findUserById(userId); // Busca el usuario por su ID utilizando el servicio de usuarios.
		
		// Si el post ya está en la lista de posts guardados del usuario, lo elimina.
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		} else {
			user.getSavedPost().add(post); // Si el post no está en la lista de posts guardados del usuario, lo añade.
			userRepository.save(user); // Guarda el usuario actualizado en el repositorio.
		}
		return post;
	}

	/**
     * Da o quita 'me gusta' a un post por parte de un usuario.
     *
     * @param postId -> Recibe el ID del post a dar o quitar 'me gusta'.
     * @param userId -> Recibe el ID del usuario que da o quita 'me gusta'.
     * @return El post actualizado.
     * @throws Exception -> Si el post o el usuario no se encuentra.
     */
	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		
		Post post = findPostById(postId); // Busca el post por su ID utilizando el método findPostById.
		
		User user = userService.findUserById(userId); // Busca el usuario por su ID utilizando el servicio de usuarios.
		
		// Si el usuario ya ha dado 'me gusta' al post lo quita, sino lo añade.
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		} else {
			post.getLiked().add(user);
		}
		
		//post.getLiked().add(user);

		return postRepository.save(post);
	}

}