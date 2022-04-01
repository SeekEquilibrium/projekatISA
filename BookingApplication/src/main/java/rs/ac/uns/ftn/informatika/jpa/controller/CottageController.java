package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import rs.ac.uns.ftn.informatika.jpa.model.Cottage;
import rs.ac.uns.ftn.informatika.jpa.service.CottageService;
import rs.ac.uns.ftn.informatika.jpa.dto.CottageDTO;

@CrossOrigin
@RestController
@RequestMapping("/cottage")
public class CottageController {
	
	@Autowired
	private CottageService cottageService;
	
	@GetMapping("/{name}")
	public ResponseEntity<CottageDTO> getCottage(@PathVariable String name){
		Cottage cottage = cottageService.findCottage(name);
		
		if(cottage == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
	}
}
