package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.dto.UserDTO;
import rs.ac.uns.ftn.informatika.jpa.model.UserApp;
import rs.ac.uns.ftn.informatika.jpa.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/signup")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
		if(userService.UserExists(userDTO.getEmail())) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		UserApp userApp = new UserApp(userDTO.getName(), userDTO.getSurname(), 
				userDTO.getEmail(), userDTO.getPassword(), userDTO.getPhoneNumber(), 
				userDTO.getUserType());
		userApp = userService.save(userApp);
		return new ResponseEntity<>(new UserDTO(userApp), HttpStatus.OK);
	}
}
