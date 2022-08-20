package com.bookingapplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class CottageImage extends ImageApp {
	@ManyToOne(fetch = FetchType.EAGER)
	private Cottage cottage;

	public CottageImage() {
		super();
	}

	public CottageImage(Cottage cottage) {
		super();
		this.cottage = cottage;
	}
	
	public CottageImage(ImageApp imageApp, Cottage cottage) {
		super(imageApp.getId(), imageApp.getPath());
		this.cottage = cottage;
	}

	public CottageImage(String path, Cottage cottage) {
		super(path);
		this.cottage = cottage;
	}

	public Cottage getCottage() {
		return cottage;
	}

	public void setCottage(Cottage cottage) {
		this.cottage = cottage;
	}
	
	
}
