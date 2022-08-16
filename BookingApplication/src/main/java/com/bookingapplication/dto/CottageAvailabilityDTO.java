package com.bookingapplication.dto;

import com.bookingapplication.model.AppointmentCottage;

import java.time.LocalDate;

public class CottageAvailabilityDTO {
    private long cottageId;
    private LocalDate date;
    private boolean hasAction;
    private double pricePerDay;

    public CottageAvailabilityDTO(AppointmentCottage appointmentCottage) {
        this.cottageId = appointmentCottage.getCottage().getId();
        this.date = appointmentCottage.getDate();
        this.hasAction = appointmentCottage.isHasAction();
        this.pricePerDay = appointmentCottage.getPricePerDay();
    }

    public long getCottageId() {
        return cottageId;
    }

    public void setCottageId(long cottageId) {
        this.cottageId = cottageId;
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

    public double isPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
