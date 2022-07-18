package com.bookingapplication.service;

import java.util.ArrayList;

import com.bookingapplication.model.CottageImage;
import com.bookingapplication.repository.CottageImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CottageImageService {
	@Autowired
	private CottageImageRepository cottageImageRepository;
	
	public ArrayList<CottageImage> getCottageImages(long id) {
		return cottageImageRepository.findByCottageId(id);
	}
	
	public ArrayList<String> findImagePathsByCottageId(long id){
		return cottageImageRepository.findImagePathsByCottageId(id);
	}

	public CottageImage save (CottageImage image){
		return cottageImageRepository.save(image);
	}
}
