package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.dto.CottageDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Cottage;
import rs.ac.uns.ftn.informatika.jpa.repository.CottageRepository;

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
}
