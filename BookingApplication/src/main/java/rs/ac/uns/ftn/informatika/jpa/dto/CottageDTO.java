package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Cottage;

public class CottageDTO {
	private String name;
	private String address;
	private String description;
	private Integer roomNumber;
	private Integer bedNumber;
	private String rules;
	private UserDTO cottageOwner;
	private ImagesDTO cottageImages;
	
	public CottageDTO(String name, String address, String description, Integer roomNumber, 
			Integer bedNumber, String rules, UserDTO cottageOwner, ImagesDTO cottageImages) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.roomNumber = roomNumber;
		this.bedNumber = bedNumber;
		this.rules = rules;
		this.cottageOwner = cottageOwner;
		this.cottageImages = cottageImages;
	}
	
	public CottageDTO(String name, String address, String description, Integer roomNumber, 
			Integer bedNumber, String rules, UserDTO cottageOwner) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.roomNumber = roomNumber;
		this.bedNumber = bedNumber;
		this.rules = rules;
		this.cottageOwner = cottageOwner;
	}

	public CottageDTO(Cottage cottage) {
		this(cottage.getName(), cottage.getAddress(), cottage.getDescription(), cottage.getRoomNumber(),
				cottage.getBedNumber(), cottage.getRules(), new UserDTO(cottage.getCottageOwner()));
	}
	
	public CottageDTO(Cottage cottage, ImagesDTO cottageImages) {
		this(cottage.getName(), cottage.getAddress(), cottage.getDescription(),  cottage.getRoomNumber(),
				cottage.getBedNumber(), cottage.getRules(), new UserDTO(cottage.getCottageOwner()));
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

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(Integer bedNumber) {
		this.bedNumber = bedNumber;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}
	
	
}
