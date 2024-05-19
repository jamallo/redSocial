package com.jamallo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamallo.exceptions.ExcepcionesUsuario;
import com.jamallo.models.User;
import com.jamallo.repository.UserRepository;
import com.jamallo.service.UserService;


// Controlador REST para manejar las solicitudes relacionadas con los usuarios.
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	
	
	/**
     * Endpoint para obtener todos los usuarios.
     *
     * @return Lista de todos los usuarios.
     */
	@GetMapping("/api/users")
	public List<User> getUsers() {
		
		// Obtiene todos los usuarios.
		List<User> users=userRepository.findAll();
		
		return users;
	}
	
	/**
     * Endpoint para obtener un usuario por su ID.
     *
     * @param id -> Recibe el ID del usuario.
     * @return El usuario con el ID especificado.
     * @throws ExcepcionesUsuario -> Si el usuario no se encuentra.
     */
	@GetMapping("/api/users/{userId}")
	public User getUserById(
			@PathVariable("userId") Integer id
			) throws ExcepcionesUsuario {
		
		// Obtiene un usuario por su ID.
		User user=userService.findUserById(id);
		
		return user;
	}
	
	
	
	
	/**
     * Endpoint para actualizar un usuario.
     *
     * @param jwt-> Recibe el token JWT del usuario.
     * @param user -> Recibe los datos del usuario a actualizar.
     * @return El usuario actualizado.
     * @throws ExcepcionesUsuario Si ocurre un error durante la actualización.
     */
	@PutMapping("/api/users")
	public User updateUser(
			@RequestHeader("Authorization") String jwt, 
			@RequestBody User user
			) throws ExcepcionesUsuario {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User updatedUser=userService.updateUser(user, reqUser.getId());
		
		return updatedUser;
	}
	
	/**
     * Endpoint para seguir a otro usuario.
     *
     * @param jwt -> Recibe el token JWT del usuario que realiza la solicitud.
     * @param userId2 -> Recibe el ID del usuario a seguir.
     * @return El usuario seguido.
     * @throws ExcepcionesUsuario Si ocurre un error durante la operación.
     */
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(
			@RequestHeader("Authorization") String jwt, 
			@PathVariable Integer userId2
			) throws ExcepcionesUsuario {
		
		User reqUser = userService.findUserByJwt(jwt);
		User user = userService.followUser(reqUser.getId() , userId2);
		return user;
	}
	
	/**
     * Endpoint para buscar usuarios por una consulta específica.
     *
     * @param query -> Recibe la consulta de búsqueda.
     * @return Lista de usuarios que coinciden con la consulta.
     */
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users= userService.searchUser(query);
		
		return users;
	}
	
	/**
     * Endpoint para obtener los datos del usuario desde el token JWT.
     *
     * @param jwt -> Recibe el token JWT del usuario.
     * @return El usuario correspondiente al token JWT.
     */
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
		
		User user = userService.findUserByJwt(jwt);
		
		user.setPassword(null); // Para no devolver la contraseña en la respuesta
		
		return user;
	}

}
