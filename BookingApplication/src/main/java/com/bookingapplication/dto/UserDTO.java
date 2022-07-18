package com.bookingapplication.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.bookingapplication.model.Role;
import com.bookingapplication.model.UserApp;

public class UserDTO {
	private String name;
	private String surname;
	private String username;
	private String email;
	private String phoneNumber;

	public UserDTO(UserApp userApp) {
		this(userApp.getName(), userApp.getSurname(), userApp.getUsername(), userApp.getEmail(), userApp.getPhoneNumber());
	}

	public UserDTO(String name, String surname, String username, String email, String phoneNumber) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public UserDTO() {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}