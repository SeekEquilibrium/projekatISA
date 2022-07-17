package com.bookingapplication.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.bookingapplication.dto.RegisterCottageRequestDTO;
import com.bookingapplication.model.Cottage;
import com.bookingapplication.model.UserApp;
import com.bookingapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.bookingapplication.service.CottageImageService;
import com.bookingapplication.service.CottageService;
import com.bookingapplication.dto.CottageDTO;
import com.bookingapplication.dto.ImagesDTO;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cottage")
public class CottageController {

	@Autowired
	private CottageService cottageService;
	@Autowired
	private CottageImageService cottageImageService;
	@Autowired
	private UserService userService;

	@GetMapping("/{name}")
	public ResponseEntity<CottageDTO> getCottage(@PathVariable String name){
		Cottage cottage = cottageService.findCottage(name);

		if(cottage == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
//		ArrayList<CottageImage> images = cottageImageService.getCottageImages(cottage.getId());
		ArrayList<String> images = cottageImageService.findImagePathsByCottageId(cottage.getId());
		ImagesDTO imagesDto = new ImagesDTO(images);
		return new ResponseEntity<>(new CottageDTO(cottage, imagesDto), HttpStatus.OK);
	}

	@PutMapping(path="/edit")
	public ResponseEntity<CottageDTO> updateCottage(@RequestBody CottageDTO cottageDTO){
		Cottage cottage = cottageService.editCottage(cottageDTO);
		return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
	}

	@PostMapping("/register")
	@PreAuthorize("hasAuthority('COTTAGE_OWNER')")
	public ResponseEntity<Cottage> registerCottage(@RequestBody MultipartFile[] files){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		UserApp cottageOwner = userService.FindUserByUsername(user.getName());
		Cottage cottage = new Cottage();
		return new ResponseEntity<>(cottage, HttpStatus.OK);
	}


}
