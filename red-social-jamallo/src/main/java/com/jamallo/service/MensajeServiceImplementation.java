package com.jamallo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamallo.models.Chat;
import com.jamallo.models.Mensaje;
import com.jamallo.models.User;
import com.jamallo.repository.ChatRepository;
import com.jamallo.repository.MensajeRepository;

@Service
public class MensajeServiceImplementation implements MensajeService {
	
	@Autowired
	private MensajeRepository mensajeRepository;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Mensaje createMensaje(User user, Integer chatId, Mensaje req) throws Exception {
		
		Chat chat = chatService.findChatById(chatId);

		Mensaje mensaje = new Mensaje();
				
		mensaje.setChat(chat);
		mensaje.setContenido(req.getContenido());
		mensaje.setImagen(req.getImagen());
		mensaje.setUser(user);
		mensaje.setTiempo(LocalDateTime.now());
		
		Mensaje savedMensaje = mensajeRepository.save(mensaje);
		
		chat.getMensajes().add(savedMensaje);
		chatRepository.save(chat);
		
		return savedMensaje;
	}

	@Override
	public List<Mensaje> findChatsMensajes(Integer chatId) throws Exception {
		
		Chat chat = chatService.findChatById(chatId);
		
		return mensajeRepository.findByChatId(chatId);
	}

}
