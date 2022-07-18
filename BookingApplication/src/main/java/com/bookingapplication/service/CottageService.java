package com.bookingapplication.service;

import com.bookingapplication.dto.EditCottageRequestDTO;
import com.bookingapplication.dto.RegisterCottageRequestDTO;
import com.bookingapplication.model.Cottage;
import com.bookingapplication.model.CottageImage;
import com.bookingapplication.model.CottageOwner;
import com.bookingapplication.repository.CottageRepository;
import com.bookingapplication.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingapplication.dto.CottageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class CottageService {
	
	@Autowired
	private CottageRepository cottageReposiotry;
	@Autowired
	private CottageImageService cottageImageService;

	public Cottage findCottage(String name) {
		return cottageReposiotry.findByNameIgnoringCase(name);
	}

	public Boolean cottageExists(String name) { return cottageReposiotry.existsByName(name); }

	public Cottage editCottage(EditCottageRequestDTO cottageDTO) {
		Cottage cottage = cottageReposiotry.findById(cottageDTO.getId());
		cottage.setName(cottageDTO.getName());
		cottage.setAddress(cottageDTO.getAddress());
		cottage.setDescription(cottageDTO.getDescription());
		cottage.setRules(cottageDTO.getRules());
		cottage.setRoomNumber(cottageDTO.getRoomNumber());
		cottage.setBedNumber(cottageDTO.getBedNumber());
		return cottageReposiotry.save(cottage);
	}

	public Cottage registerCottage(RegisterCottageRequestDTO requestDTO, CottageOwner cottageOwner) throws IOException {
		Cottage cottage = new Cottage(
				requestDTO.getName(),
				requestDTO.getAddress(),
				requestDTO.getDescription(),
				requestDTO.getRoomNumber(),
				requestDTO.getBedNumber(),
				requestDTO.getRules(),
				cottageOwner
		);
		Set<CottageImage> cottageImages = new HashSet<>();
		if(requestDTO.getFiles() != null){
			for(MultipartFile image : requestDTO.getFiles()){
				String fileName = cottage.getName() + "_" + UUID.randomUUID() + ".png";
				FileUploadUtil.saveFile(FileUploadUtil.getImageFolder("cottages"), fileName, image);
				CottageImage cottageImage = new CottageImage(fileName, cottage);
				cottageImages.add(cottageImage);
			}
		}
		cottage.setCottageImages(cottageImages);
		cottageReposiotry.save(cottage);
		return cottage;
	}
}
