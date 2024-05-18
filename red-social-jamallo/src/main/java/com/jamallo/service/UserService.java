package com.jamallo.service;

import java.util.List;

import com.jamallo.exceptions.ExcepcionesUsuario;
import com.jamallo.models.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public User findUserById(Integer userId) throws ExcepcionesUsuario;
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer userId1, Integer userId2) throws ExcepcionesUsuario;
	
	public User updateUser(User user, Integer userId) throws ExcepcionesUsuario;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);

}
