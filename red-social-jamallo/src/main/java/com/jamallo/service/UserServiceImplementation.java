package com.jamallo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamallo.config.JwtProvider;
import com.jamallo.models.User;
import com.jamallo.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository userRepository;
	

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

	@Override
	public User findUserById(Integer userId) throws Exception {
		
		Optional<User> user=userRepository.findById(userId);
		
		if(user.isPresent()) {
			
			return user.get();
		}
		
		throw new Exception("el usuario no existe con ese id " + userId);
	}

	@Override
	public User findUserByEmail(String email) {
		
		User user=userRepository.findByEmail(email);
		
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws Exception {
		
		User reqUser = findUserById(reqUserId);
		
		User user2= findUserById(userId2);
		
		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowings().add(user2.getId());
		
		userRepository.save(reqUser);
		userRepository.save(user2);
		
		return reqUser;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception {
		
		Optional<User> user1 = userRepository.findById(userId);
		
		if (user1.isEmpty()) {
			throw new Exception("usuario no existe con ese id " + userId);
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

	@Override
	public List<User> searchUser(String query) {
		return userRepository.searchUser(query);
	}
	
    // Instancia de JwtProvider
    private JwtProvider jwtProvider = new JwtProvider();

	@Override
	public User findUserByJwt(String jwt) {
		
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		
		User user = userRepository.findByEmail(email);
		return user;
	}

}
