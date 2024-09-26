package com.generation.tropico.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.generation.tropico.model.entities.User;
import com.generation.tropico.model.repository.UserRepository;
import com.generation.tropico.web.Session;

public class LoginAPI {

	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	Session session;
	
	
	@GetMapping("/currentuser")
	public User getCurrentUser() {
		return session.getCurrentUser();
	}
	
	
	@PostMapping("/login")
	public User login(@RequestBody User user) {

		User match = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

		if (match == null) {
			session.setCurrentUser(match);
		}

		return match != null ? match : User.NOONE;
	}
	
	
	@PostMapping("/logout")
	public void logout() {
        session.setCurrentUser(User.NOONE);
        return User.NOONE;
	
	}
	
}
