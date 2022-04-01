package rs.ac.uns.ftn.informatika.jpa.dto;

import rs.ac.uns.ftn.informatika.jpa.model.Cottage;

public class CottageDTO {
	private String name;
	private String address;
	private String description;
	private UserDTO cottageOwner;
	
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return address;
	}
	public void setAdress(String adress) {
		this.address = adress;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UserDTO getOwner() {
		return cottageOwner;
	}
	public void setOwner(UserDTO cottageOwner) {
		this.cottageOwner = cottageOwner;
	}
	
	
}
