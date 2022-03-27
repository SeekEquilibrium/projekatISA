package rs.ac.uns.ftn.informatika.jpa.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import rs.ac.uns.ftn.informatika.jpa.model.UserApp;
import rs.ac.uns.ftn.informatika.jpa.model.UserType;

public class UserDTO {
	private String name;
	private String surname;
	private String email;
	private String password;
	private String phoneNumber;
	@Enumerated(value = EnumType.STRING)
	private UserType userType;
	
	public UserDTO(UserApp userApp) {
		this(userApp.getName(), userApp.getSurname(), userApp.getEmail(),
				userApp.getPassword(), userApp.getPhoneNumber(), userApp.getUserType());
	}
	
	public UserDTO(String name, String surname, String email, String password, String phoneNumber, UserType userType) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.userType = userType;
	}
	
	public UserDTO() {
		super();
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
