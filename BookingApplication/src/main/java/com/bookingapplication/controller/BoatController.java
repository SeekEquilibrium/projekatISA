package com.bookingapplication.controller;

import com.bookingapplication.dto.*;
import com.bookingapplication.model.*;
import com.bookingapplication.service.BoatImageService;
import com.bookingapplication.service.BoatOwnerService;
import com.bookingapplication.service.BoatService;
import com.bookingapplication.service.UserService;
import com.bookingapplication.validation.ImageValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/boat")
public class BoatController {
    @Autowired
    private BoatService boatService;
    @Autowired
    private ImageValidation imageValidation;
    @Autowired
    private UserService userService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private BoatImageService boatImageService;


    @GetMapping("/{name}")
    public ResponseEntity<BoatInfoDTO> getCottage(@PathVariable String name){
        Boat boat = boatService.findByName(name);
        if(boat == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ArrayList<String> images = boatImageService.findImagePathsByBoatId(boat.getId());
        ImagesDTO imagesDto = new ImagesDTO(images);
        return new ResponseEntity<>(new BoatInfoDTO(boat, imagesDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('BOAT_OWNER')")
    public ResponseEntity<RegisterBoatResponseDTO> registerCottage(@Valid @ModelAttribute RegisterBoatRequestDTO requestDTO) throws IOException {
        if(boatService.existsByName(requestDTO.getName())){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        if(requestDTO.getFiles() != null){
            for (MultipartFile file : requestDTO.getFiles()) {
                if(!imageValidation.validateImageFile(file)){
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }
            }
        }
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        BoatOwner boatOwner = boatOwnerService.findById(userApp.getId());
        Boat boat = boatService.registerBoat(requestDTO, boatOwner);
        ArrayList<String> images = boatImageService.findImagePathsByBoatId(boat.getId());
        ImagesDTO imagesDTO = new ImagesDTO(images);
        return new ResponseEntity<>(new RegisterBoatResponseDTO(boat, imagesDTO), HttpStatus.OK);
    }

    @PutMapping(path="/edit")
    @PreAuthorize("hasAuthority('BOAT_OWNER')")
    public ResponseEntity<EditBoatResponseDTO> updateCottage(@Valid @ModelAttribute EditBoatRequestDTO requestDTO) throws IOException {
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        BoatOwner boatOwner = boatOwnerService.findById(userApp.getId());
        Boat existingBoat = boatService.findById(requestDTO.getId());
        if(!existingBoat.getBoatOwner().getUsername().equals(boatOwner.getUsername())){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        Boat boatWithSameName = boatService.findByName(requestDTO.getName());
        if(boatWithSameName!=null){
            if(requestDTO.getName().equals(boatWithSameName.getName())){
                if(requestDTO.getId()!=boatWithSameName.getId()){
                    return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                }
            }
        }
        Boat boat = boatService.editBoat(requestDTO);
        ArrayList<String> images = boatImageService.findImagePathsByBoatId(boat.getId());
        ImagesDTO imagesDTO = new ImagesDTO(images);
        return new ResponseEntity<>(new EditBoatResponseDTO(boat, imagesDTO), HttpStatus.OK);
    }
}
