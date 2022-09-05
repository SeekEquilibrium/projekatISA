package com.bookingapplication.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class CottageReservations extends Reservations{
    @ManyToOne(fetch = FetchType.EAGER)
    private Cottage cottage;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public CottageReservations(LocalDate dateStart, LocalDate dateEnd, ReservationStatus status, Cottage cottage, Client client) {
        super(dateStart, dateEnd, status);
        this.cottage = cottage;
        this.client = client;
    }

    public CottageReservations(long id, LocalDate dateStart, LocalDate dateEnd, ReservationStatus status, Cottage cottage, Client client) {
        super(id, dateStart, dateEnd, status);
        this.cottage = cottage;
        this.client = client;
    }

    public CottageReservations(Cottage cottage, Client client) {
        this.cottage = cottage;
        this.client = client;
    }

    public CottageReservations(){

    }

    public CottageReservations(Cottage cottage) {
        this.cottage = cottage;
    }

    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
