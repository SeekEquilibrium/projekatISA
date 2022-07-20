package com.bookingapplication.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AppointmentCottage extends Appointment {
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER)
    private Cottage cottage;

    @Column
    private Double pricePerDay;

    @Column
    @Enumerated(EnumType.STRING)
    private AppointmentType type;

    public AppointmentCottage() {
    }

    public AppointmentCottage(Cottage cottage, Double pricePerDay, AppointmentType type) {
        this.cottage = cottage;
        this.pricePerDay = pricePerDay;
        this.type = type;
    }

    public AppointmentCottage(LocalDateTime date, boolean hasAction, Cottage cottage, Double pricePerDay, AppointmentType type) {
        super(date, hasAction);
        this.cottage = cottage;
        this.pricePerDay = pricePerDay;
        this.type = type;
    }

    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
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
