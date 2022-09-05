package com.bookingapplication.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AppointmentCottage extends Appointment {
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER)
    private Cottage cottage;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @Column
    private Double pricePerDay;

    @Column
    @Enumerated(EnumType.STRING)
    private AppointmentType type;

    @Version
    @Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
    private Long version = 0L;

    public AppointmentCottage() {
    }

    public AppointmentCottage(Cottage cottage, Double pricePerDay, AppointmentType type) {
        this.cottage = cottage;
        this.pricePerDay = pricePerDay;
        this.type = type;
    }

    public AppointmentCottage(LocalDate date, boolean hasAction, Cottage cottage, Double pricePerDay, AppointmentType type) {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
