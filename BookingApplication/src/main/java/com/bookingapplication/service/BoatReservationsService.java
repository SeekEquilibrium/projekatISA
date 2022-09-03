package com.bookingapplication.service;

import com.bookingapplication.model.BoatReservations;
import com.bookingapplication.repository.BoatReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoatReservationsService {
    @Autowired
    private BoatReservationsRepository boatReservationsRepository;

    public ArrayList<BoatReservations> getBoatReservations(long boatId){
        return boatReservationsRepository.getBoatReservations(boatId);
    }

    public BoatReservations save(BoatReservations boatReservations){
        return boatReservationsRepository.save(boatReservations);
    }
}
