package com.bookingapplication.dto;

import com.bookingapplication.model.Cottage;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EditCottageResponseDTO {
    private long id;
    private String name;
    private String address;
    private String description;
    private Integer roomNumber;
    private Integer bedNumber;
    private String rules;
    private ImagesDTO cottageImages;

    public EditCottageResponseDTO(long id, String name, String address, String description, Integer roomNumber, Integer bedNumber, String rules, ImagesDTO cottageImages) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
        this.rules = rules;
        this.cottageImages = cottageImages;
    }

    public EditCottageResponseDTO(Cottage cottage, ImagesDTO cottageImages) {
        this(cottage.getId(), cottage.getName(), cottage.getAddress(), cottage.getDescription(),  cottage.getRoomNumber(),
                cottage.getBedNumber(), cottage.getRules(), cottageImages);
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

    public ImagesDTO getCottageImages() {
        return cottageImages;
    }

    public void setCottageImages(ImagesDTO cottageImages) {
        this.cottageImages = cottageImages;
    }
}
