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

@RestController
public class CreateMensaje {
	
	@Autowired
	private MensajeService mensajeService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/mensajes/chat/{chatid}")
	public Mensaje createMensaje(
			@RequestBody Mensaje req,
			@RequestHeader("Authorization") String jwt,
			@PathVariable Integer chatId
			) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		Mensaje mensaje = mensajeService.createMensaje(user, chatId, req);
		
		return mensaje;
	}
	
	@GetMapping("/api/mensajes/chat/{chatid}")
	public List<Mensaje> findChatMensaje(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Integer chatId
			) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		List<Mensaje> mensajes = mensajeService.findChatsMensajes(chatId);
		
		return mensajes;
	}

}
