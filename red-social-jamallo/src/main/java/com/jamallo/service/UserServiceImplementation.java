package com.jamallo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamallo.config.JwtProvider;
import com.jamallo.exceptions.ExcepcionesUsuario;
import com.jamallo.models.User;
import com.jamallo.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository userRepository;
	

	 /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param user -> Recibe el usuario a registrar.
     * @return El usuario registrado y guardado.
     */
	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setNombre(user.getNombre());
		newUser.setApellidos(user.getApellidos());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());
		
		User savedUser=userRepository.save(newUser);
		
		return savedUser;
	}

	/**
     * Encuentra un usuario por su ID.
     *
     * @param userId -> Recibe el ID del usuario a buscar.
     * @return El usuario encontrado.
     * @throws ExcepcionesUsuario Si el usuario no se encuentra.
     */
	@Override
	public User findUserById(Integer userId) throws ExcepcionesUsuario {
		
		Optional<User> user=userRepository.findById(userId);
		
		if(user.isPresent()) {
			
			return user.get();
		}
		
		throw new ExcepcionesUsuario("el usuario no existe con ese id " + userId);
	}

	/**
     * Encuentra un usuario por su email.
     *
     * @param email -> Recibe el email del usuario a buscar.
     * @return El usuario encontrado.
     */
	@Override
	public User findUserByEmail(String email) {
		
		User user=userRepository.findByEmail(email);
		
		return user;
	}

	/**
     * Permite a un usuario seguir a otro usuario.
     *
     * @param reqUserId -> Recibe el ID del usuario que quiere seguir.
     * @param userId2 -> Recibe el ID del usuario a ser seguido.
     * @return El usuario que hizo la solicitud de seguir.
     * @throws ExcepcionesUsuario -> Si alguno de los usuarios no se encuentra.
     */
	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws ExcepcionesUsuario {
		
		User reqUser = findUserById(reqUserId);
		
		User user2= findUserById(userId2);
		
		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowings().add(user2.getId());
		
		userRepository.save(reqUser);
		userRepository.save(user2);
		
		return reqUser;
	}

	/**
     * Actualiza la información de un usuario existente.
     *
     * @param user -> Recibe los nuevos datos del usuario.
     * @param userId -> Recibe el ID del usuario a actualizar.
     * @return El usuario actualizado.
     * @throws ExcepcionesUsuario -> Si el usuario no se encuentra.
     */
	@Override
	public User updateUser(User user, Integer userId) throws ExcepcionesUsuario {
		
		Optional<User> user1 = userRepository.findById(userId);
		
		if (user1.isEmpty()) {
			throw new ExcepcionesUsuario("usuario no existe con ese id " + userId);
		}
		
		User oldUser= user1.get();
		
		if(user.getNombre()!=null) {
			oldUser.setNombre(user.getNombre());
		}
		
		if(user.getApellidos()!=null) {
			oldUser.setApellidos(user.getApellidos());
		}
		
		if(user.getEmail()!=null) {
			oldUser.setEmail(user.getEmail());
		}
		
		if (user.getGenero() != null) {
			oldUser.setGenero(user.getGenero());
		}
		
		User updateUser=userRepository.save(oldUser);
		
		return updateUser;
	}

	/**
     * Busca usuarios basándose en una consulta.
     *
     * @param query -> Recibe la consulta de búsqueda.
     * @return La lista de usuarios que coinciden con la consulta.
     */
	@Override
	public List<User> searchUser(String query) {
		return userRepository.searchUser(query);
	}
	
	// Instancia de JwtProvider para gestionar JWT.
    private JwtProvider jwtProvider = new JwtProvider();

    /**
     * Encuentra un usuario a partir de un token JWT.
     *
     * @param jwt -> Recibe el token JWT.
     * @return El usuario encontrado.
     */
	@Override
	public User findUserByJwt(String jwt) {
		
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		
		User user = userRepository.findByEmail(email);
		return user;
	}

}
