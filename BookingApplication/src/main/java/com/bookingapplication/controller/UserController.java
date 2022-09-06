package com.bookingapplication.controller;

import com.bookingapplication.dto.*;
import com.bookingapplication.model.UserApp;
import com.bookingapplication.service.DeleteAccountRequestService;
import com.bookingapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private DeleteAccountRequestService deleteAccountRequestService;
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

    @PostMapping("/deleteRequest")
    public ResponseEntity<?> deleteRequest(@Valid @RequestBody DeleteAccountRequestDTO request) {
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        boolean response = deleteAccountRequestService.deleteAccountRequest(userApp, request.getReason());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/deleteResponse")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> adminDeleteResponse(@Valid @RequestBody AdminDeleteResponseDTO response) {
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        boolean deletionResponse = deleteAccountRequestService.adminDeletionResponse(response.getRequestId(), response.isAcceptDeletion());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
