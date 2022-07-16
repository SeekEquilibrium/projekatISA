package com.bookingapplication.model;

import com.bookingapplication.dto.RegistrationRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class UserApp {
	@Id
	@SequenceGenerator(name = "userAppSeqGen", sequenceName = "userAppSeq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userAppSeqGen")
	@Column(name="id", unique=true, nullable=false)
	private long id;
	@Column
	private String name;
	@Column
	private String surname;
	@Column(nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private String username;
	@Column
	private String password;
	@Column
	private String phoneNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;

	
	public UserApp(long id, String username, String name, String surname, String email, String password, String phoneNumber,
				   Role role) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}

	public UserApp(String username, String name, String surname, String email, String password, String phoneNumber, Role role) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}

	public UserApp() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}