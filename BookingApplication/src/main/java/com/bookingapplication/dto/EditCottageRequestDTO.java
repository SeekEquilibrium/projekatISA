package com.bookingapplication.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EditCottageRequestDTO {
    @NotBlank
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String description;
    @NotNull
    private Integer roomNumber;
    @NotNull
    private Integer bedNumber;
    private String rules;
    private String[] deletedImages;
    private MultipartFile[] files;

    public EditCottageRequestDTO(long id, String name, String address, String description, Integer roomNumber, Integer bedNumber, String rules) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
        this.rules = rules;
    }

    public EditCottageRequestDTO() {
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

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String[] getDeletedImages() {
        return deletedImages;
    }

    public void setDeletedImages(String[] deletedImages) {
        this.deletedImages = deletedImages;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
}
