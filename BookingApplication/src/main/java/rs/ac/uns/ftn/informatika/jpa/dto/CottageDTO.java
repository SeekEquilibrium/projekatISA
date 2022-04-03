package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Cottage;

public class CottageDTO {
	private String name;
	private String address;
	private String description;
	private UserDTO cottageOwner;
	private ImagesDTO cottageImages;
	
	public CottageDTO(String name, String address, String description, UserDTO cottageOwner, ImagesDTO cottageImages) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.cottageOwner = cottageOwner;
		this.cottageImages = cottageImages;
	}
	
	public CottageDTO(String name, String address, String description, UserDTO cottageOwner) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.cottageOwner = cottageOwner;
	}

	public CottageDTO(Cottage cottage) {
		this(cottage.getName(), cottage.getAddress(), cottage.getDescription(), new UserDTO(cottage.getCottageOwner()));
	}
	
	public CottageDTO(Cottage cottage, ImagesDTO cottageImages) {
		this(cottage.getName(), cottage.getAddress(), cottage.getDescription(), new UserDTO(cottage.getCottageOwner()));
		this.cottageImages = cottageImages;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String adress) {
		this.address = adress;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UserDTO getCottageOwner() {
		return cottageOwner;
	}
	public void setCottageOwner(UserDTO cottageOwner) {
		this.cottageOwner = cottageOwner;
	}

	public ImagesDTO getCottageImages() {
		return cottageImages;
	}

	public void setCottageImages(ImagesDTO cottageImages) {
		this.cottageImages = cottageImages;
	}
	
	
}
