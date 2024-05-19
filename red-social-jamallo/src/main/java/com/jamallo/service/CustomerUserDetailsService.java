package com.jamallo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jamallo.models.User;
import com.jamallo.repository.UserRepository;

// Carga detalles específicos del usuario durante el proceso de autenticación.
@Service // Anotación que indica que esta clase es un servicio de Spring, gestionado por el contenedor de Spring.
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired // Inyección de dependencias para que Spring gestione la instancia de UserRepository.
	private UserRepository userRepository;
	
	/**
	 * Este método se llama durante el proceso de autenticación de Spring Security.
	 * 
	 * @param username -> Recibe el nombre de usuario (email).
	 * @return Una instancia de UserDetails con el email, contraseña y las autorizaciones del usuario
	 * @throws UsernameNotFoundException -> Excepción por si no se encuentra el usuario.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// Busca al usuario en la base de datos usando el email proporcionado.
		User user = userRepository.findByEmail(username);
		
		// Si no se encuentra al usuario, se lanza una excepción indicando que el usuario no fue encontrado.
		if(user==null) {
			throw new UsernameNotFoundException("usuarios no encontrado con el email" + username);
		}
		
		// Crea una lista vacía de autoridades (roles) del usuario.
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

}
