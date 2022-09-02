package com.bookingapplication.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EditBoatRequestDTO {
    @NotNull
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String description;
    private String rules;
    private String[] deletedImages;
    private MultipartFile[] files;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

    public EditBoatRequestDTO(long id, String name, String address, String description, String rules, String[] deletedImages, MultipartFile[] files, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.rules = rules;
        this.deletedImages = deletedImages;
        this.files = files;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
