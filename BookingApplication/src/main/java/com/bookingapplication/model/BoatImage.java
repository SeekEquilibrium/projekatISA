package com.bookingapplication.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class BoatImage extends ImageApp {
    @ManyToOne(fetch = FetchType.EAGER)
    private Boat boat;

    public BoatImage(String path, Boat boat){
        super(path);
        this.boat = boat;
    }

    public BoatImage(){
        super();
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}
