package com.bookingapplication.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class DefineCottageAvailabilityRequestDTO {
    @NotNull
    private long cottageId;
    @NotNull
    private double pricePerDay;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;

    public DefineCottageAvailabilityRequestDTO(long cottageId, double pricePerDay, LocalDateTime startDate, LocalDateTime endDate) {
        this.cottageId = cottageId;
        this.pricePerDay = pricePerDay;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DefineCottageAvailabilityRequestDTO() {
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
