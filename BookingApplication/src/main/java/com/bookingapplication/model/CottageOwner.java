package com.bookingapplication.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CottageOwner extends UserApp {
	@JsonIgnore
	@OneToMany
	(mappedBy = "cottageOwner", fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	public Set<Cottage> cottages = new HashSet<Cottage>();

	//Obrazlozenje za registraciju
	@Column
	private String reasoning;

	public CottageOwner() {
		super();
	}
	
	public CottageOwner(UserApp userApp, String reasoning) {
		super(userApp.getUsername(), userApp.getName(), userApp.getSurname(), userApp.getEmail(), userApp.getPassword(),
				userApp.getPhoneNumber(), userApp.getRole());
		this.reasoning = reasoning;
	}

	public String getReasoning() {
		return reasoning;
	}

	public void setReasoning(String reasoning) {
		this.reasoning = reasoning;
	}
}
