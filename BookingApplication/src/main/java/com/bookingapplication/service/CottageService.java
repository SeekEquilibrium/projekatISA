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

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class CottageService {
	
	@Autowired
	private CottageRepository cottageReposiotry;
	@Autowired
	private CottageImageService cottageImageService;

	public Cottage findByName(String name) { return cottageReposiotry.findByNameIgnoringCase(name);	}

	public Cottage findById(long id) {
		return cottageReposiotry.findById(id);
	}

	public Boolean existsByName(String name) { return cottageReposiotry.existsByName(name); }

	public Boolean existsById(long id) { return cottageReposiotry.existsById(id); }

	public Cottage editCottage(EditCottageRequestDTO cottageDTO) throws IOException {
		Cottage cottage = cottageReposiotry.findById(cottageDTO.getId());
		cottage.setName(cottageDTO.getName());
		cottage.setAddress(cottageDTO.getAddress());
		cottage.setDescription(cottageDTO.getDescription());
		cottage.setRules(cottageDTO.getRules());
		cottage.setRoomNumber(cottageDTO.getRoomNumber());
		cottage.setBedNumber(cottageDTO.getBedNumber());

		Set<CottageImage> cottageImages = cottage.getCottageImages();
		if(cottageDTO.getDeletedImages()!=null){
			for(String path : cottageDTO.getDeletedImages()){
				cottageImages.removeIf(image -> image.getPath().equals(path));
			}
		}

		if(cottageDTO.getFiles() != null){
			for(MultipartFile image : cottageDTO.getFiles()){
				String fileName = cottage.getName() + "_" + UUID.randomUUID() + ".png";
				FileUploadUtil.saveFile(FileUploadUtil.getImageFolder("cottages"), fileName, image);
				CottageImage cottageImage = new CottageImage(fileName, cottage);
				cottageImages.add(cottageImage);
			}
		}
		cottage.setCottageImages(cottageImages);
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

	public boolean ownerOwnsCottage(long cottageOwnerId, long cottageId){
		//odraditi ovaj deo preko query
		return findById(cottageId).getCottageOwner().equals(cottageOwnerId);
	}

	public ArrayList<Cottage> getOwnerCottages(long cottageOwnerId){
		return cottageReposiotry.findByCottageOwnerId(cottageOwnerId);
	}
}
