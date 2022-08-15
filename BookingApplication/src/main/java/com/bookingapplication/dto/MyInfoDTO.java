package com.bookingapplication.dto;

import com.bookingapplication.model.UserApp;

public class MyInfoDTO {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String role;

    public MyInfoDTO(Long id, String name, String surname, String username, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.role = role;
    }

    public MyInfoDTO() {
    }

    public MyInfoDTO(UserApp user, String role) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String phoneNumber) {
        this.role = phoneNumber;
    }
}
