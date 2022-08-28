package com.bookingapplication.controller;

import com.bookingapplication.dto.EditProfileRequestDTO;
import com.bookingapplication.dto.JwtAuthenticationRequest;
import com.bookingapplication.dto.MyInfoDTO;
import com.bookingapplication.dto.UserTokenState;
import com.bookingapplication.model.UserApp;
import com.bookingapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/whoami")
    public  MyInfoDTO getMyInfo(Principal user) {
        UserApp userApp = userService.FindUserByUsername(user.getName());
        return new MyInfoDTO(userApp, userApp.getRole().getName());
    }

    @PostMapping("/editProfile")
    public ResponseEntity<?> editProfile(@Valid @RequestBody EditProfileRequestDTO editProfileRequestDTO) {
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userApp.getUsername(), editProfileRequestDTO.getPassword()));

        UserApp existingUsername = userService.FindUserByUsername(editProfileRequestDTO.getUsername());
        if(existingUsername!=null && existingUsername.getId()!=userApp.getId()){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(userService.edit(editProfileRequestDTO, userApp.getId()), HttpStatus.OK);
    }
}
