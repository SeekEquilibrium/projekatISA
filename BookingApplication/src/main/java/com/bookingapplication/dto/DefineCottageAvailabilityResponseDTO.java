package com.bookingapplication.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class DefineCottageAvailabilityResponseDTO {
    private long cottageId;
    private double pricePerDay;
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;

    public DefineCottageAvailabilityResponseDTO(long cottageId, double pricePerDay, LocalDateTime startDate, LocalDateTime endDate) {
        this.cottageId = cottageId;
        this.pricePerDay = pricePerDay;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
