package com.bookingapplication.dto.reservationInfo;

import java.util.ArrayList;

public class CottageReservationsInfoDTO {
    private ArrayList<ReservationDTO> reservations;
    private double allTimeIncome;
    private double lastMonthIncome;

    public CottageReservationsInfoDTO(ArrayList<ReservationDTO> reservations, double allTimeIncome, double lastMonthIncome) {
        this.reservations = reservations;
        this.allTimeIncome = allTimeIncome;
        this.lastMonthIncome = lastMonthIncome;
    }

    public ArrayList<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    public double getAllTimeIncome() {
        return allTimeIncome;
    }

    public void setAllTimeIncome(double allTimeIncome) {
        this.allTimeIncome = allTimeIncome;
    }

    public double getLastMonthIncome() {
        return lastMonthIncome;
    }

    public void setLastMonthIncome(double lastMonthIncome) {
        this.lastMonthIncome = lastMonthIncome;
    }
}
