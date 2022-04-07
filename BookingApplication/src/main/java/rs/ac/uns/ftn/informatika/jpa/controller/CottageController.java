package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import rs.ac.uns.ftn.informatika.jpa.model.Cottage;
import rs.ac.uns.ftn.informatika.jpa.model.CottageImage;
import rs.ac.uns.ftn.informatika.jpa.service.CottageImageService;
import rs.ac.uns.ftn.informatika.jpa.service.CottageService;
import rs.ac.uns.ftn.informatika.jpa.dto.CottageDTO;
import rs.ac.uns.ftn.informatika.jpa.dto.ImagesDTO;

@CrossOrigin
@RestController
@RequestMapping("/cottage")
public class CottageController {
	
	@Autowired
	private CottageService cottageService;
	@Autowired
	private CottageImageService cottageImageService;
	
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
		Cottage cottage =cottageService.editCottage(cottageDTO);
		return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
	}
	
	
}
