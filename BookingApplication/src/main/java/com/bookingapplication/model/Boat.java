package com.bookingapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Boat {
    @Id
    @SequenceGenerator(name = "boatSeqGen", sequenceName = "boatSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "boatSeqGen")
    private long id;
    @Column(unique = true)
    private String name;
    @Column
    private String address;
    @Column
    private Double longitude;
    @Column
    private Double latitude;
    @Column(columnDefinition="TEXT")
    private String description;
    @Column(columnDefinition="TEXT")
    private String rules;
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER)
    private BoatOwner boatOwner;
    @OneToMany
            (mappedBy = "boat", fetch = FetchType.EAGER, cascade= CascadeType.ALL, orphanRemoval=true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Set<BoatImage> boatImages = new HashSet<BoatImage>();

    public Boat(){

    }

    public Boat(String name, String address, Double longitude, Double latitude, String description, String rules, BoatOwner boatOwner) {
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.rules = rules;
        this.boatOwner = boatOwner;
    }

    public Boat(long id, String name, String address, Double longitude, Double latitude, String description, String rules, BoatOwner boatOwner) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.rules = rules;
        this.boatOwner = boatOwner;
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

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }

    public Set<BoatImage> getBoatImages() {
        return boatImages;
    }

    public void setBoatImages(Set<BoatImage> boatImages) {
        this.boatImages = boatImages;
    }
}
