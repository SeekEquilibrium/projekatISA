package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.CottageImage;
import rs.ac.uns.ftn.informatika.jpa.repository.CottageImageRepository;

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
}
