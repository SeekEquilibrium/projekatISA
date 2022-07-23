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

	@OneToMany
	(mappedBy = "cottageOwner", fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	public Set<CottageReport> reports = new HashSet<>();

	@ManyToOne(fetch = FetchType.EAGER)
	private UserApp owner;
	@ManyToOne(fetch = FetchType.EAGER)
	private Client client;

	public CottageOwner() {
		super();
	}
	
	public CottageOwner(UserApp userApp) {
		super(userApp.getUsername(), userApp.getName(), userApp.getSurname(), userApp.getEmail(), userApp.getPassword(),
				userApp.getPhoneNumber(), userApp.getRole());
	}
}
