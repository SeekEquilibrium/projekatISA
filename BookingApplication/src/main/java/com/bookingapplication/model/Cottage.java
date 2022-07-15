package com.bookingapplication.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cottage {
	@Id
	@SequenceGenerator(name = "cottageSeqGen", sequenceName = "cottageSeqGen", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cottageSeqGen")
	private long id;
	@Column(unique = true)
	private String name;
	@Column
	private String address;
	@Column(length = 1000)
	private String description;
	@Column
	private Integer roomNumber;
	@Column
	private Integer bedNumber;
	@Column
	private String rules;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.EAGER)
	private CottageOwner cottageOwner;
	@JsonIgnore
	@OneToMany
	(mappedBy = "cottage", fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	public Set<CottageImage> cottageImages = new HashSet<CottageImage>();
	
	public Cottage() {}

	public Cottage(long id, String name, String address, String description, Integer roomNumber, 
			Integer bedNumber, String rules, CottageOwner cottageOwner) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.roomNumber = roomNumber;
		this.bedNumber = bedNumber;
		this.rules = rules;
		this.cottageOwner = cottageOwner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CottageOwner getCottageOwner() {
		return cottageOwner;
	}

	public void setCottageOwner(CottageOwner cottageOwner) {
		this.cottageOwner = cottageOwner;
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

	public Set<CottageImage> getCottageImages() {
		return cottageImages;
	}

	public void setCottageImages(Set<CottageImage> cottageImages) {
		this.cottageImages = cottageImages;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	
}