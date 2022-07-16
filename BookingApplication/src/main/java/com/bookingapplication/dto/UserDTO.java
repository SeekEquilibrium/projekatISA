package com.bookingapplication.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.bookingapplication.model.Role;
import com.bookingapplication.model.UserApp;

public class UserDTO {
	private String name;
	private String surname;
	private String email;
	private String password;
	private String phoneNumber;
	@Enumerated(value = EnumType.STRING)
	private Role userType;
	
	public UserDTO(UserApp userApp) {
		this(userApp.getName(), userApp.getSurname(), userApp.getEmail(),
				userApp.getPassword(), userApp.getPhoneNumber(), userApp.getRole());
	}
	
	public UserDTO(String name, String surname, String email, String password, String phoneNumber, Role userType) {
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
	public Role getUserType() {
		return userType;
	}
	public void setUserType(Role userType) {
		this.userType = userType;
	}
}
