package com.jamallo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jamallo.models.Mensaje;
import com.jamallo.models.User;
import com.jamallo.service.MensajeService;
import com.jamallo.service.UserService;

// Controlador REST para manejar las operaciones relacionadas con los mensajes, 
// como la creación de nuevos mensajes y la recuperación de mensajes de un chat específico.
@RestController
public class CreateMensaje {
	
	// Servicio para manejar la lógica de negocio relacionada con los mensajes.
	@Autowired
	private MensajeService mensajeService;
	
	// Servicio para manejar la lógica de negocio relacionada con los usuarios.
	@Autowired
	private UserService userService;
	
	/**
     * Endpoint para crear un nuevo mensaje en un chat específico.
     * 
     * @param req -> Recibe el mensaje a crear.
     * @param jwt -> Recibe el token JWT del usuario que realiza la solicitud.
     * @param chatId -> Recibe el ID del chat donde se creará el mensaje como un parámetro de la URL.
     * @return El mensaje creado.
     * @throws Exception -> Si ocurre algún error durante la creación del mensaje.
     */
	@PostMapping("/api/mensajes/chat/{chatid}")
	public Mensaje createMensaje(
			@RequestBody Mensaje req,
			@RequestHeader("Authorization") String jwt,
			@PathVariable Integer chatId
			) throws Exception {
		
		// Encuentra el usuario a partir del JWT
		User user = userService.findUserByJwt(jwt);
		
		// Crea el mensaje utilizando el servicio de mensajes
		Mensaje mensaje = mensajeService.createMensaje(user, chatId, req);
		
		return mensaje;
	}
	
	/**
     * Endpoint para obtener los mensajes de un chat específico.
     * 
     * @param jwt -> Recibe el token JWT del usuario que realiza la solicitud.
     * @param chatId -> Recibe el ID del chat cuyos mensajes se desean obtener como un parámetro de la URL.
     * @return Una lista de mensajes del chat especificado.
     * @throws Exception -> Si ocurre algún error durante la recuperación de los mensajes.
     */
	@GetMapping("/api/mensajes/chat/{chatid}")
	public List<Mensaje> findChatMensaje(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Integer chatId
			) throws Exception {
		
		// Encuentra el usuario a partir del JWT
		User user = userService.findUserByJwt(jwt);
		
		// Obtiene los mensajes del chat utilizando el servicio de mensajes
		List<Mensaje> mensajes = mensajeService.findChatsMensajes(chatId);
		
		return mensajes;
	}

}
