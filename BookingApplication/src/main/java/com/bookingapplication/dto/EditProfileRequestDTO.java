package com.bookingapplication.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EditProfileRequestDTO {
    @NotEmpty
    private String password;
    private String newPassword;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
