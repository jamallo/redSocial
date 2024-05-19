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

@Service // Marca esta clase como un servicio gestionado por el contenedor de Spring.
public class MensajeServiceImplementation implements MensajeService {
	
	@Autowired // Inyección de dependencias para obtener una instancia del repositorio de mensajes.
	private MensajeRepository mensajeRepository;
	
	@Autowired // Inyección de dependencias para obtener una instancia del servicio de chats.
	private ChatService chatService;
	
	@Autowired // Inyección de dependencias para obtener una instancia del repositorio de chats.
	private ChatRepository chatRepository;

	/**
     * Crea y guarda un nuevo mensaje en la base de datos.
     *
     * @param user -> Recibe el usuario que envía el mensaje.
     * @param chatId -> Recibe el ID del chat al que pertenece el mensaje.
     * @param req -> Recibe el mensaje que se va a crear, contiene el contenido y la imagen (si la hay).
     * @return El mensaje guardado.
     * @throws Exception -> Si el chat no se encuentra o ocurre algún otro error.
     */
	@Override
	public Mensaje createMensaje(User user, Integer chatId, Mensaje req) throws Exception {
		
		// Busca el chat por su ID utilizando el servicio de chats.
		Chat chat = chatService.findChatById(chatId);

		// Crea una nueva instancia de Mensaje y configura sus propiedades.
		Mensaje mensaje = new Mensaje();
				
		mensaje.setChat(chat); // Asocia el mensaje con el chat encontrado.
		mensaje.setContenido(req.getContenido()); // Establece el contenido del mensaje a partir de la solicitud.
		mensaje.setImagen(req.getImagen()); // Establece la imagen del mensaje a partir de la solicitud, si la hay.
		mensaje.setUser(user); // Asocia el mensaje con el usuario que lo envió.
		mensaje.setTiempo(LocalDateTime.now()); // Establece el tiempo de creación del mensaje a la hora actual.
		
		Mensaje savedMensaje = mensajeRepository.save(mensaje); // Guarda el mensaje en el repositorio y obtiene el mensaje guardado.
		
		chat.getMensajes().add(savedMensaje); // Añade el mensaje guardado a la lista de mensajes del chat.
		chatRepository.save(chat); // Guarda el chat actualizado en el repositorio.
		
		return savedMensaje;
	}

	/**
     * Devuelve la lista de mensajes de un chat específico.
     *
     * @param chatId -> Recibe el ID del chat cuyos mensajes se desean recuperar.
     * @return La lista de mensajes asociados al chat.
     * @throws Exception -> Si el chat no se encuentra o ocurre algún otro error.
     */
	@Override
	public List<Mensaje> findChatsMensajes(Integer chatId) throws Exception {
		
		Chat chat = chatService.findChatById(chatId); // Busca el chat por su ID utilizando el servicio de chats.
		
		return mensajeRepository.findByChatId(chatId);
	}

}
