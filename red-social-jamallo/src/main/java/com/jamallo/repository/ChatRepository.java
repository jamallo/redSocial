package com.jamallo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jamallo.models.Chat;
import com.jamallo.models.User;


// Declara que esta interfaz extiende JpaRepository, 
// lo que le proporciona métodos CRUD básicos para gestionar entidades Chat. 
// El tipo de la entidad es Chat y la clave primaria es de tipo Integer.
public interface ChatRepository extends JpaRepository<Chat, Integer>{
	
	/**
     * Encuentra todos los chats asociados a un usuario específico por su ID.
     *
     * @param userId -> Recibe el ID del usuario.
     * @return Una lista de chats asociados al usuario.
     */
	public List<Chat> findByUsersId(Integer userId);
	
	/**
     * Encuentra un chat específico entre dos usuarios.
     *
     * @param user -> Recibe uno de los usuarios del chat.
     * @param reqUser -> Recibe el otro usuario del chat.
     * @return El chat encontrado entre los dos usuarios.
     */
	@Query("select c from Chat c Where :user Member of c.users And :reqUser Member of c.users")
	public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);

}
