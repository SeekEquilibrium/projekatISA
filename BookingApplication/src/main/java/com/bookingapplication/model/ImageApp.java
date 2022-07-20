package com.bookingapplication.model;

import javax.persistence.*;

@MappedSuperclass
public class ImageApp {
	@Id
	@SequenceGenerator(name = "imageSeqGen", sequenceName = "imageSeqGen", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "imageSeqGen")
	private long id;
	@Column(unique = true)
	private String path;
	
	public ImageApp() {
		super();
	}

	public ImageApp(long id, String path) {
		super();
		this.id = id;
		this.path = path;
	}

	public ImageApp(String path) {
		super();
		this.path = path;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
