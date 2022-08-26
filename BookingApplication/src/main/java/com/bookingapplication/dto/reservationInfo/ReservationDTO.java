package com.bookingapplication.dto.reservationInfo;

import com.bookingapplication.dto.UserDTO;

import java.time.LocalDate;

public class ReservationDTO {
    private long id;
    private UserDTO user;
    private LocalDate date;
    private double pricePerNight;
    private boolean hasAction;

    public ReservationDTO(long id, UserDTO user, LocalDate date, double pricePerNight, boolean hasAction) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.pricePerNight = pricePerNight;
        this.hasAction = hasAction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isHasAction() {
        return hasAction;
    }

    public void setHasAction(boolean hasAction) {
        this.hasAction = hasAction;
    }
}
