package com.bookingapplication.service;

import com.bookingapplication.dto.RegisterCottageRequestDTO;
import com.bookingapplication.model.Cottage;
import com.bookingapplication.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingapplication.dto.CottageDTO;

@Service
public class CottageService {
	
	@Autowired
	private CottageRepository cottageReposiotry;
	
	public Cottage findCottage(String name) {
		return cottageReposiotry.findByNameIgnoringCase(name);
	}
	
	public Cottage editCottage(CottageDTO cottageDTO) {
		Cottage cottage = cottageReposiotry.findById(cottageDTO.getId());
		cottage.setName(cottageDTO.getName());
		cottage.setAddress(cottageDTO.getAddress());
		cottage.setDescription(cottageDTO.getDescription());
		cottage.setRules(cottageDTO.getRules());
		cottage.setRoomNumber(cottageDTO.getRoomNumber());
		cottage.setBedNumber(cottageDTO.getBedNumber());
		return cottageReposiotry.save(cottage);
	}

//	public Cottage register(RegisterCottageRequestDTO requestDTO){
//	}
}
