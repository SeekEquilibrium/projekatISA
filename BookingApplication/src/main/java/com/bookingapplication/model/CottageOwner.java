package com.bookingapplication.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CottageOwner extends UserApp {
	@JsonIgnore
	@OneToMany
	(mappedBy = "cottageOwner", fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	public Set<Cottage> cottages = new HashSet<Cottage>();
	
	public CottageOwner() {
		super();
	}
	
	public CottageOwner(UserApp userApp) {
		super(userApp.getId(), userApp.getName(), userApp.getSurname(), userApp.getEmail(), userApp.getPassword(), userApp.getPhoneNumber(), userApp.getUserType());
	}
}
