package com.jamallo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamallo.models.Chat;
import com.jamallo.models.User;
import com.jamallo.repository.ChatRepository;

// Implementación de la interfaz ChatService para manejar 
// la lógica de negocio relacionada con los chats.
@Service
public class ChatServiceImplementation implements ChatService {
	
	@Autowired
	private ChatRepository chatRepository;

	/**
     * Crea un nuevo chat entre dos usuarios.
     * Contiene la lógica para evitar la creación de chats duplicados entre los mismos usuarios, 
     * lo que optimiza el uso del almacenamiento y evita redundancias en la base de datos.
     * 
     * @param reqUser -> Recibe el usuario que hace la solicitud.
     * @param user2 -> Recibe el otro usuario con el que se quiere iniciar el chat.
     * @return El chat creado o el chat existente si ya hay uno entre los usuarios.
     */
	@Override
	public Chat createChat(
			User reqUser, 
			User user2
			) {
		
		// Comprueba si ya existe un chat entre los usuarios especificados.
		 Chat siExiste = chatRepository.findChatByUsersId(user2, reqUser);
		 
		 // Si ya existe, lo devuelve.
		 if (siExiste != null) {
			 return siExiste;
		 }
		 
		 // Si no existe, crea un nuevo chat, lo guarda en el repositorio y lo devuelve.
		 Chat chat = new Chat();
		 chat.getUsers().add(user2);
		 chat.getUsers().add(reqUser);
		 chat.setTiempo(LocalDateTime.now());
		 
		 return chatRepository.save(chat);
	}

	/**
     * Encuentra un chat por su ID.
     * 
     * @param chatId -> Recibe el ID del chat.
     * @return El chat correspondiente al ID.
     * @throws Exception Si el chat no se encuentra.
     */
	@Override
	public Chat findChatById(Integer chatId) throws Exception {

		Optional<Chat> opt = chatRepository.findById(chatId);
		
		if (opt.isEmpty()) {
			throw new Exception ("Chat no encontrado con el id - " + chatId);
		}
		
		return opt.get();
	}

	/**
     * Encuentra todos los chats de un usuario por su ID.
     * 
     * @param userId -> Recibe el ID del usuario.
     * @return Lista de chats en los que participa el usuario.
     */
	@Override
	public List<Chat> findUsersChat(Integer userId) {
		return chatRepository.findByUsersId(userId);
	}



}
