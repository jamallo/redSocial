package com.jamallo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamallo.config.JwtProvider;
import com.jamallo.models.User;
import com.jamallo.repository.UserRepository;
import com.jamallo.request.LoginRequest;
import com.jamallo.response.AuthResponse;
import com.jamallo.service.CustomerUserDetailsService;
import com.jamallo.service.UserService;

//Controlador REST para manejar las operaciones de autenticación 
//como el registro (signup) y el inicio de sesión (signin).

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	// Servicio para manejar la lógica de negocio relacionada con los usuarios.
	@Autowired
	private UserService userService;
	
	// Repositorio para interactuar con la base de datos de usuarios
	@Autowired
	private UserRepository userRepository;
	
	// Codificador de contraseñas para cifrar y verificar contraseñas.
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// Servicio para cargar los detalles del usuario desde la base de datos.
	@Autowired
	private CustomerUserDetailsService customerUserDetails;
	
    // Instancia de JwtProvider utilizada para generar y validar tokens JWT.
    private JwtProvider jwtProvider = new JwtProvider();
	
    /**
     * Endpoint para registrar un nuevo usuario.
     * 
     * @param user -> Los detalles del usuario a registrar.
     * @return Una respuesta de autenticación con un token JWT.
     * @throws Exception -> Si el email ya está en uso.
     */
	@PostMapping("/signup")
	public AuthResponse createUser(
			@RequestBody User user
			) throws Exception {
		
		// Verifica si el email ya está registrado
		User isExist = userRepository.findByEmail(user.getEmail());
		
		if (isExist != null) {
			throw new Exception("Este email ya está usado por otra cuenta");
		}
		
		// Crea un nuevo usuario y guarda sus detalles
		User newUser = new User();
		
		newUser.setEmail(user.getEmail());
		newUser.setNombre(user.getNombre());
		newUser.setApellidos(user.getApellidos());
		newUser.setPassword(passwordEncoder.encode(user.getPassword())); //Codifica la contraseña del usuario
		
		//guarda los detalles del nuevo usuario en la base de datos.
		User savedUser=userRepository.save(newUser);
		
		// Autentica al nuevo usuario
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		// Genera un token JWT para el usuario registrado
		String token = jwtProvider.generateToken(authentication);
		
		// Retorna la respuesta de autenticación con el token generado
		AuthResponse res = new AuthResponse(token, "Suceso registrado");
		
		return res;

	}


	 /**
     * Endpoint para iniciar sesión de un usuario existente.
     * 
     * @param loginRequest -> Los detalles de inicio de sesión (email y contraseña).
     * @return Una respuesta de autenticación con un token JWT.
     */
	@PostMapping ("/signin")
	public AuthResponse signin(
			@RequestBody LoginRequest loginRequest
			) {
		
		// Autentica al usuario con las credenciales proporcionadas
		Authentication authentication = authenticate(
				loginRequest.getEmail(), 
				loginRequest.getPassword()
				);

		// Genera un token JWT para el usuario autenticado
		String token = jwtProvider.generateToken(authentication);
		
		// Retorna la respuesta de autenticación con el token generado
		AuthResponse res = new AuthResponse(
				token, 
				"sesión iniciada"
				);
		
		return res;
	}

	/**
     * Autentica al usuario con el email y la contraseña proporcionados.
     * 
     * @param email -> El email del usuario.
     * @param password -> La contraseña del usuario.
     * @return Una instancia de Authentication si las credenciales son válidas.
     * @throws BadCredentialsException -> Si las credenciales son incorrectas.
     */
	private Authentication authenticate (
			String email, 
			String password
			) {
		
		// Carga los detalles del usuario por email desde la base de datos
		UserDetails userDetails = customerUserDetails.loadUserByUsername(email);
		
		// Si está vacío la verificación lanza una excepción de usuario incorrecto.
		if (userDetails == null) {
			throw new BadCredentialsException("nombre de usuario inválido");
		}
		
		// Verifica si la contraseña proporcionada coincide con la almacenada
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("contraseña no correcta");
		}
		
		// Retorna una instancia de Authentication si las credenciales son válidas
		return new UsernamePasswordAuthenticationToken(
				userDetails, 
				null, 
				userDetails.getAuthorities()
				);
	}

}
