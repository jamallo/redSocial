package com.jamallo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamallo.models.Reels;
import com.jamallo.models.User;
import com.jamallo.repository.ReelsRepository;

@Service
public class ReelsServiceImplementation implements ReelsService {
	
	@Autowired
	private ReelsRepository reelsRepository;
	
	@Autowired
	private UserService userService;

	/**
     * Crea y guarda un nuevo reel en la base de datos.
     *
     * @param reel -> Recibe el reel a crear.
     * @param user -> Recibe el usuario que crea el reel.
     * @return El reel creado y guardado.
     */
	@Override
	public Reels createReel(Reels reel, User user) {
		
		Reels createReel = new Reels(); // Crea una nueva instancia de Reels y configura sus propiedades.
		
		createReel.setTitulo(reel.getTitulo()); // Establece el título del reel a partir de la solicitud.
		createReel.setUser(user); // Asocia el reel con el usuario que lo crea.
		createReel.setVideo(reel.getVideo()); // Establece el video del reel a partir de la solicitud.
		
		return reelsRepository.save(createReel);
	}

	/**
     * Encuentra todos los reels.
     *
     * @return La lista de todos los reels.
     */
	@Override
	public List<Reels> findAllReels() {
		return reelsRepository.findAll();
	}

	/**
     * Encuentra todos los reels creados por un usuario específico.
     *
     * @param userId -> Recibe el ID del usuario cuyos reels se desean recuperar.
     * @return La lista de reels creados por el usuario.
     * @throws Exception -> Si el usuario no se encuentra.
     */
	@Override
	public List<Reels> findUserReel(Integer userId) throws Exception {

		userService.findUserById(userId); // Busca el usuario por su ID utilizando el servicio de usuarios.

		return reelsRepository.findByUserId(userId);
	}

}
