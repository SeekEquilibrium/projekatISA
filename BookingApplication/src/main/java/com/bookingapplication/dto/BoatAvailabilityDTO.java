package com.bookingapplication.dto;

import com.bookingapplication.model.AppointmentBoat;

import java.time.LocalDate;

public class BoatAvailabilityDTO {
    private long boatId;
    private LocalDate date;
    private boolean hasAction;
    private double pricePerDay;

    public BoatAvailabilityDTO() {
    }

    public BoatAvailabilityDTO(AppointmentBoat appointmentBoat) {
        this.boatId = appointmentBoat.getId();
        this.date = appointmentBoat.getDate();
        this.hasAction = appointmentBoat.isHasAction();
        this.pricePerDay = appointmentBoat.getPricePerDay();
    }

    public long getBoatId() {
        return boatId;
    }

    public void setBoatId(long boatId) {
        this.boatId = boatId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isHasAction() {
        return hasAction;
    }

    public void setHasAction(boolean hasAction) {
        this.hasAction = hasAction;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
