package com.bookingapplication.controller;

import com.bookingapplication.dto.RegistrationRequestDTO;
import com.bookingapplication.model.UserApp;
import com.bookingapplication.service.UserService;
import com.bookingapplication.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserApp> addUser(@RequestBody RegistrationRequestDTO request) {
        if(userService.UserExists(request.getUsername())){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        UserApp user = userService.register(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
