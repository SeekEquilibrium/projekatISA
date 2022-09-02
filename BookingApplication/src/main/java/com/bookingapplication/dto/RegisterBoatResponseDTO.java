package com.bookingapplication.dto;

import com.bookingapplication.model.Boat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegisterBoatResponseDTO {
    private long id;
    private String name;
    private String address;
    private String description;
    private String rules;
    private ImagesDTO boatImages;
    private Double latitude;
    private Double longitude;

    public RegisterBoatResponseDTO(long id, String name, String address, String description, String rules, ImagesDTO boatImages, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.rules = rules;
        this.boatImages = boatImages;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public RegisterBoatResponseDTO() {
    }

    public RegisterBoatResponseDTO(Boat boat, ImagesDTO boatImages){
        this(boat.getId(), boat.getName(), boat.getAddress(), boat.getDescription(), boat.getRules(), boatImages, boat.getLatitude(), boat.getLongitude());
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

    public ImagesDTO getBoatImages() {
        return boatImages;
    }

    public void setBoatImages(ImagesDTO boatImages) {
        this.boatImages = boatImages;
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
