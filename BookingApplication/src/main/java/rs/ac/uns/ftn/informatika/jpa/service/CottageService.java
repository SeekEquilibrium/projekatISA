package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.model.Cottage;
import rs.ac.uns.ftn.informatika.jpa.repository.CottageRepository;

@Service
public class CottageService {
	
	@Autowired
	private CottageRepository cottageReposiotry;
	
	public Cottage findCottage(String name) {
		return cottageReposiotry.findByNameIgnoringCase(name);
	}
}
