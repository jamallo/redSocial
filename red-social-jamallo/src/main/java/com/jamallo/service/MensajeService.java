package com.jamallo.service;

import java.util.List;

import com.jamallo.models.Mensaje;
import com.jamallo.models.User;

public interface MensajeService {
	
	public Mensaje createMensaje (
			User user, 
			Integer chatId, 
			Mensaje req
			) throws Exception;
	
	public List<Mensaje> findChatsMensajes(Integer chatId) throws Exception;

}
