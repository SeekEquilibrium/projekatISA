package com.bookingapplication.controller;

import com.bookingapplication.dto.JwtAuthenticationRequest;
import com.bookingapplication.dto.RegistrationRequestDTO;
import com.bookingapplication.dto.UserTokenState;
import com.bookingapplication.model.UserApp;
import com.bookingapplication.service.UserService;
import com.bookingapplication.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    public ResponseEntity<UserApp> addUser(@Valid @RequestBody RegistrationRequestDTO request) {
        if(userService.UserExists(request.getUsername())){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        //Obican korisnik ne treba da pise obrazlozenje za registraciju, dok ostali moraju
        if(!request.getRole().equals("CLIENT") && (request.getReasoning().equals("") || request.getReasoning()==null)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        UserApp user = userService.register(request);
        if(user==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) {

        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        UserApp user = (UserApp) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }
}
