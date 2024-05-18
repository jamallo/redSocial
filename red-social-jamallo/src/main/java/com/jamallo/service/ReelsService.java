package com.jamallo.service;

import java.util.List;

import com.jamallo.models.Reels;
import com.jamallo.models.User;

public interface ReelsService {
	
	public Reels createReel (Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReel(Integer userId) throws Exception;

}
