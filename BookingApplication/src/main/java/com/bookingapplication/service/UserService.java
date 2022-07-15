package com.bookingapplication.service;

import com.bookingapplication.model.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingapplication.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserApp save(UserApp userApp) {
		return userRepository.save(userApp);
	}
	
	public boolean UserExists(String email) {
		return userRepository.existsByEmail(email);
	}
}
