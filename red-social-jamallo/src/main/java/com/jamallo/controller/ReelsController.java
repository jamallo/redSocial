package com.jamallo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jamallo.models.Reels;
import com.jamallo.models.User;
import com.jamallo.service.ReelsService;
import com.jamallo.service.UserService;


// Controlador REST para manejar las solicitudes relacionadas con los reels.
@RestController
public class ReelsController {

	@Autowired
	private ReelsService reelsService;
	
	@Autowired
	private UserService userService;
	
	/**
     * Endpoint para crear un nuevo reel.
     *
     * @param reel -> Recibe el objeto Reels que se va a crear.
     * @param jwt -> Recibe el token JWT del usuario.
     * @return El reel creado.
     */
	@PostMapping("/api/reels")
	public Reels createReels(
			@RequestBody Reels reel, 
			@RequestHeader("Authorization") String jwt
			) {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Reels createdReels = reelsService.createReel(reel, reqUser);
		
		return createdReels;
	}
	
	/**
     * Endpoint para obtener todos los reels.
     *
     * @return La lista de todos los reels.
     */
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {
				
		List<Reels> reels = reelsService.findAllReels();
		
		return reels;
	}
	
	/**
     * Endpoint para obtener todos los reels de un usuario por su ID.
     *
     * @param userId-> Recibe el ID del usuario.
     * @return La lista de reels del usuario.
     * @throws Exception -> Si ocurre un error durante la búsqueda de los reels.
     */
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUserReels(
			@PathVariable Integer userId
			) throws Exception {
				
		List<Reels> reels = reelsService.findUserReel(userId);
		
		return reels;
	}
}
