package com.bookingapplication.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class BoatReport extends Report {
    @ManyToOne(fetch = FetchType.EAGER)
    private Boat boat;

    public BoatReport(UserApp owner, Client client, String description, LocalDateTime date, Boolean reportClient , Boolean didNotShowUp, Boat boat) {
        super(description, date, reportClient, didNotShowUp, client, owner);
        this.boat = boat;
    }

    public BoatReport(Boat boat) {
        this.boat = boat;
    }

    public BoatReport(){

    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}
