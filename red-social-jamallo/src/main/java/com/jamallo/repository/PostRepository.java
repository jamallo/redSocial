package com.jamallo.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.jamallo.models.Post;

/**
 * Repositorio para la entidad Post.
 * Proporciona métodos CRUD y métodos personalizados para gestionar publicaciones en la base de datos.
 */
public interface PostRepository extends JpaRepository <Post, Integer>{
	
	/**
     * Encuentra todas las publicaciones asociadas a un usuario específico por su ID.
     *
     * @param userId -> Recibe el ID del usuario.
     * @return Una lista de publicaciones asociadas al usuario.
     */
	@Query("select p from Post p where p.user.id=:userId")
	List<Post> findPostByUserId(Integer userId);

}
