package com.bookingapplication.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class BoatReservations extends Reservations{
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER)
    private Boat boat;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public BoatReservations(LocalDate dateStart, LocalDate dateEnd, ReservationStatus status, Boat boat, Client client) {
        super(dateStart, dateEnd, status);
        this.boat = boat;
        this.client = client;
    }

    public BoatReservations(long id, LocalDate dateStart, LocalDate dateEnd, ReservationStatus status, Boat boat, Client client) {
        super(id, dateStart, dateEnd, status);
        this.boat = boat;
        this.client = client;
    }

    public BoatReservations(Boat boat, Client client) {
        this.boat = boat;
        this.client = client;
    }

    public BoatReservations() {
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
