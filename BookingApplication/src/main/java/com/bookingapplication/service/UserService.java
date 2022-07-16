package com.bookingapplication.service;

import com.bookingapplication.dto.RegistrationRequestDTO;
import com.bookingapplication.model.CottageOwner;
import com.bookingapplication.model.Role;
import com.bookingapplication.model.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookingapplication.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleService roleService;

	public UserApp register(RegistrationRequestDTO request) {
		UserApp user = new UserApp();
		user.setName(request.getName());
		user.setSurname(request.getSurname());
		user.setEmail(request.getEmail());
		user.setUsername(request.getUsername());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		switch(request.getRole()){
			case "COTTAGE_OWNER": {
				user.setRole(roleService.findRole(request.getRole()));
				return save(new CottageOwner(user));
			}
			default:
				return null;
		}
	}

	public UserApp save(UserApp user) {
		return userRepository.save(user);
	}
	
	public boolean UserExists(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
}
