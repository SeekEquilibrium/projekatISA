package com.bookingapplication.dto.reservationInfo;

import com.bookingapplication.dto.UserDTO;

import java.time.LocalDate;

public class ReservationDTO {
    private long id;
    private UserDTO user;
    private LocalDate startDate;
    private LocalDate endDate;
    private String cottageName;
    private long cottageId;
    private String status;

    public ReservationDTO(long id, UserDTO user, LocalDate startDate, LocalDate endDate, String cottageName, long cottageId, String status) {
        this.id = id;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cottageName = cottageName;
        this.cottageId = cottageId;
        this.status = status;
    }

    public ReservationDTO() {
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

    public String getCottageName() {
        return cottageName;
    }

    public void setCottageName(String cottageName) {
        this.cottageName = cottageName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCottageId() {
        return cottageId;
    }

    public void setCottageId(long cottageId) {
        this.cottageId = cottageId;
    }
}
