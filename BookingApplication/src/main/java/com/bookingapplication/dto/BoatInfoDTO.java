package com.bookingapplication.dto;

import com.bookingapplication.model.Boat;

import javax.persistence.Column;

public class BoatInfoDTO {
    private long id;
    private String name;
    private String address;
    private Double longitude;
    private Double latitude;
    private String description;
    private String rules;
    private UserDTO boatOwner;
    private ImagesDTO cottageImages;

    public BoatInfoDTO(){}

    public BoatInfoDTO(long id, String name, String address, Double longitude, Double latitude, String description, String rules, UserDTO boatOwner) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.rules = rules;
        this.boatOwner = boatOwner;
    }

    public BoatInfoDTO(Boat boat, ImagesDTO imagesDTO){
        this(boat.getId(), boat.getName(), boat.getAddress(), boat.getLongitude(), boat.getLatitude(), boat.getDescription(), boat.getRules(), new UserDTO(boat.getBoatOwner()));
        this.cottageImages = imagesDTO;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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

    public UserDTO getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(UserDTO boatOwner) {
        this.boatOwner = boatOwner;
    }

    public ImagesDTO getCottageImages() {
        return cottageImages;
    }

    public void setCottageImages(ImagesDTO cottageImages) {
        this.cottageImages = cottageImages;
    }
}
