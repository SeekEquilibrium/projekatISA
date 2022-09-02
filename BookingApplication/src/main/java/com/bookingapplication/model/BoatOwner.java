package com.bookingapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BoatOwner extends UserApp {
    @JsonIgnore
    @OneToMany
            (mappedBy = "boatOwner", fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    public Set<Boat> boats = new HashSet<Boat>();
    @Column
    private String reasoning;

    public BoatOwner(UserApp userApp, String reasoning) {
        super(userApp.getUsername(), userApp.getName(), userApp.getSurname(), userApp.getEmail(), userApp.getPassword(), userApp.getPhoneNumber(), userApp.getRole());
        this.reasoning = reasoning;
    }

    public BoatOwner(){
        super();
    }

    public Set<Boat> getBoats() {
        return boats;
    }

    public void setBoats(Set<Boat> boats) {
        this.boats = boats;
    }

    public String getReasoning() {
        return reasoning;
    }

    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }
}
