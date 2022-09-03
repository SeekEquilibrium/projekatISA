package com.bookingapplication.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class AppointmentBoat extends Appointment {

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER)
    private Boat boat;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @Column
    private Double pricePerDay;

    @Column
    @Enumerated(EnumType.STRING)
    private AppointmentType type;

    public AppointmentBoat(LocalDate date, boolean hasAction, Boat boat, Double pricePerDay, AppointmentType type) {
        super(date, hasAction);
        this.boat = boat;
        this.pricePerDay = pricePerDay;
        this.type = type;
    }

    public AppointmentBoat() {
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

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }
}
