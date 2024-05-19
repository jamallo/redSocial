package com.jamallo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamallo.models.Mensaje;

/**
 * Repositorio para la entidad Mensaje.
 * Proporciona métodos CRUD para gestionar mensajes en la base de datos.
 */
public interface MensajeRepository extends JpaRepository<Mensaje, Integer>{
	
	/**
     * Encuentra todos los mensajes asociados a un chat específico por su ID.
     *
     * @param chatId -> Recibe el ID del chat.
     * @return Una lista de mensajes asociados al chat.
     */
	public List<Mensaje> findByChatId(Integer chatId);

}
