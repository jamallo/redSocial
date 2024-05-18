package com.jamallo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamallo.models.Chat;
import com.jamallo.models.User;
import com.jamallo.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService {
	
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Chat createChat(
			User reqUser, 
			User user2
			) {
		
		 Chat siExiste = chatRepository.findChatByUsersId(user2, reqUser);
		 
		 if (siExiste != null) {
			 return siExiste;
		 }

		 Chat chat = new Chat();
		 chat.getUsers().add(user2);
		 chat.getUsers().add(reqUser);
		 chat.setTiempo(LocalDateTime.now());
		 
		 return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {

		Optional<Chat> opt = chatRepository.findById(chatId);
		
		if (opt.isEmpty()) {
			throw new Exception ("Chat no encontrado con el id - " + chatId);
		}
		
		return opt.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		// TODO Auto-generated method stub
		return chatRepository.findByUsersId(userId);
	}



}
