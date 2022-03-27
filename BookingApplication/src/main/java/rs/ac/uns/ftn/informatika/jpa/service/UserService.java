package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.UserApp;
import rs.ac.uns.ftn.informatika.jpa.repository.UserRepository;

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
