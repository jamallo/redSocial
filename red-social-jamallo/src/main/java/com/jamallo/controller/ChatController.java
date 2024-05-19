package com.jamallo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jamallo.models.Chat;
import com.jamallo.models.User;
import com.jamallo.request.CreateChatRequest;
import com.jamallo.service.ChatService;
import com.jamallo.service.UserService;

// Controlador REST para manejar las operaciones relacionadas con los chats, 
// como la creación de nuevos chats y la obtención de chats de un usuario.
@RestController
public class ChatController {
	
	// Servicio para manejar la lógica de negocio relacionada con los chats.
	@Autowired
	private ChatService chatService;
	
	// Servicio para manejar la lógica de negocio relacionada con los usuarios.
	@Autowired
	private UserService userService;
	
	/**
     * Endpoint para crear un nuevo chat.
     * 
     * @param jwt -> El token JWT del usuario que realiza la solicitud.
     * @param req -> Los detalles de la solicitud para crear un chat.
     * @return El chat creado.
     * @throws Exception -> Si ocurre algún error durante la creación del chat.
     */
	@PostMapping("/api/chats")
	public Chat createChat(
			@RequestHeader("Authorization") String jwt,
			@RequestBody CreateChatRequest req
			) throws Exception {
		
		// Encuentra el usuario solicitante a partir del JWT
		User reqUser = userService.findUserByJwt(jwt);
		
		// Encuentra el segundo usuario del chat a partir de su ID
		User user2 = userService.findUserById(req.getUserId());
		
		// Crea un nuevo chat entre los dos usuarios
		Chat chat = chatService.createChat(reqUser, null);
		
		return chat;
	}
	
	/**
     * Endpoint para obtener los chats de un usuario.
     * 
     * @param jwt -> El token JWT del usuario que realiza la solicitud.
     * @return Una lista de chats del usuario.
     */
	@GetMapping("/api/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {
		
		// Encuentra el usuario a partir del JWT
		User user = userService.findUserByJwt(jwt);
		
		// Obtiene la lista de chats del usuario
		List<Chat> chats = chatService.findUsersChat(user.getId());
		
		return chats;
	}

}
