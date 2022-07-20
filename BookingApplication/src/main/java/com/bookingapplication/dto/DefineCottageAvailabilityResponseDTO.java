package com.bookingapplication.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DefineCottageAvailabilityResponseDTO {
    private long cottageId;
    private double pricePerDay;
    private boolean hasAction;
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate endDate;

    public DefineCottageAvailabilityResponseDTO(long cottageId, double pricePerDay, boolean hasAction, LocalDate startDate, LocalDate endDate) {
        this.cottageId = cottageId;
        this.pricePerDay = pricePerDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hasAction = hasAction;
    }

    public DefineCottageAvailabilityResponseDTO() {
    }

    public long getCottageId() {
        return cottageId;
    }

    public void setCottageId(long cottageId) {
        this.cottageId = cottageId;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isHasAction() {
        return hasAction;
    }

    public void setHasAction(boolean hasAction) {
        this.hasAction = hasAction;
    }

}
