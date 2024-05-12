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

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetails;
	
    // Instancia de JwtProvider
    private JwtProvider jwtProvider = new JwtProvider();
	
	//auth/signup
	@PostMapping("/signup")
	public AuthResponse createUser(
			@RequestBody User user
			) throws Exception {
		
		User isExist = userRepository.findByEmail(user.getEmail());
		
		if (isExist != null) {
			throw new Exception("Este email ya está usado por otra cuenta");
		}
		
		User newUser = new User();
		
		newUser.setEmail(user.getEmail());
		newUser.setNombre(user.getNombre());
		newUser.setApellidos(user.getApellidos());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser=userRepository.save(newUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token, "Suceso registrado");
		
		return res;

	}
	// auth/signin
	@PostMapping ("/signin")
	public AuthResponse signin(
			@RequestBody LoginRequest loginRequest
			) {
		
		
		Authentication authentication = authenticate(
				loginRequest.getEmail(), 
				loginRequest.getPassword()
				);

		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(
				token, 
				"sesión iniciada"
				);
		
		return res;
	}

	private Authentication authenticate (
			String email, 
			String password
			) {
		
		UserDetails userDetails = customerUserDetails.loadUserByUsername(email);
		
		if (userDetails == null) {
			throw new BadCredentialsException("nombre de usuario inválido");
		}
		
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("contraseña no correcta");
		}
		return new UsernamePasswordAuthenticationToken(
				userDetails, 
				null, 
				userDetails.getAuthorities()
				);
	}

}
