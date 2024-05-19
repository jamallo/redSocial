package com.jamallo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamallo.models.Reels;

/**
 * Repositorio para la entidad Reels.
 * Proporciona métodos CRUD para gestionar reels en la base de datos.
 */
public interface ReelsRepository extends JpaRepository<Reels, Integer> {
	
	/**
     * Encuentra todos los reels asociados a un usuario específico por su ID.
     *
     * @param userId -> Recibe el ID del usuario.
     * @return Una lista de reels asociados al usuario.
     */
	public List<Reels> findByUserId(Integer userId);

}
