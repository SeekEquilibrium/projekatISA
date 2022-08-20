package com.bookingapplication.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.bookingapplication.dto.*;
import com.bookingapplication.model.Cottage;
import com.bookingapplication.model.CottageOwner;
import com.bookingapplication.model.UserApp;
import com.bookingapplication.service.CottageOwnerService;
import com.bookingapplication.service.UserService;
import com.bookingapplication.validation.ImageValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.bookingapplication.service.CottageImageService;
import com.bookingapplication.service.CottageService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/cottage")
public class CottageController {

	@Autowired
	private CottageService cottageService;
	@Autowired
	private CottageImageService cottageImageService;
	@Autowired
	private CottageOwnerService cottageOwnerService;
	@Autowired
	private UserService userService;
	@Autowired
	private ImageValidation imageValidation;

	@GetMapping("/{name}")
	public ResponseEntity<CottageInfoDTO> getCottage(@PathVariable String name){
		Cottage cottage = cottageService.findByName(name);
		if(cottage == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ArrayList<String> images = cottageImageService.findImagePathsByCottageId(cottage.getId());
		ImagesDTO imagesDto = new ImagesDTO(images);
		return new ResponseEntity<>(new CottageInfoDTO(cottage, imagesDto), HttpStatus.OK);
	}

	@PutMapping(path="/edit")
	@PreAuthorize("hasAuthority('COTTAGE_OWNER')")
	public ResponseEntity<EditCottageResponseDTO> updateCottage(@RequestBody @ModelAttribute EditCottageRequestDTO requestDTO) throws IOException {
		UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		CottageOwner cottageOwner = cottageOwnerService.findById(userApp.getId());
		Cottage existingCottage = cottageService.findByName(requestDTO.getName());
		if(!existingCottage.getCottageOwner().getUsername().equals(cottageOwner.getUsername())){
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		Cottage cottage = cottageService.editCottage(requestDTO);
		ArrayList<String> images = cottageImageService.findImagePathsByCottageId(cottage.getId());
		ImagesDTO imagesDto = new ImagesDTO(images);
		return new ResponseEntity<>(new EditCottageResponseDTO(cottage, imagesDto), HttpStatus.OK);
	}

	@PostMapping("/register")
	@PreAuthorize("hasAuthority('COTTAGE_OWNER')")
	public ResponseEntity<RegisterCottageResponseDTO> registerCottage(@Valid @ModelAttribute RegisterCottageRequestDTO requestDTO) throws IOException {
		if(cottageService.existsByName(requestDTO.getName())){
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
		CottageOwner cottageOwner = cottageOwnerService.findById(userApp.getId());
		Cottage cottage = cottageService.registerCottage(requestDTO, cottageOwner);
		ArrayList<String> images = cottageImageService.findImagePathsByCottageId(cottage.getId());
		ImagesDTO imagesDto = new ImagesDTO(images);
		return new ResponseEntity<>(new RegisterCottageResponseDTO(cottage, imagesDto), HttpStatus.OK);
	}

	@GetMapping("/ownersCottages")
	@PreAuthorize("hasAuthority('COTTAGE_OWNER')")
	public ResponseEntity<ArrayList<CottageInfoDTO>> getCottage(){
		UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		CottageOwner cottageOwner = cottageOwnerService.findById(userApp.getId());
		ArrayList<Cottage> cottageList = cottageService.getOwnerCottages(userApp.getId());
		ArrayList<CottageInfoDTO> cottageInfoDTOS = new ArrayList<>();
		for (Cottage c : cottageList){
			ArrayList<String> images = cottageImageService.findImagePathsByCottageId(c.getId());
			ImagesDTO imagesDto = new ImagesDTO(images);
			CottageInfoDTO cottageInfoDTO = new CottageInfoDTO(c, imagesDto);
			cottageInfoDTOS.add(cottageInfoDTO);
		}
		return new ResponseEntity<>(cottageInfoDTOS, HttpStatus.OK);

	}


}
