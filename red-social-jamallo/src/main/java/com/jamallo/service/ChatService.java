package com.jamallo.service;

import java.util.List;

import com.jamallo.models.Chat;
import com.jamallo.models.User;

public interface ChatService {

	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById (Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat (Integer userId);
	
}
