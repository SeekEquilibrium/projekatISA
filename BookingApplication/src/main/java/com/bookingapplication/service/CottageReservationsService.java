package com.bookingapplication.service;

import com.bookingapplication.model.CottageReservations;
import com.bookingapplication.repository.CottageReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CottageReservationsService {
    @Autowired
    private CottageReservationsRepository cottageReservationsRepository;

    public CottageReservations save(CottageReservations cottageReservations){
        return cottageReservationsRepository.save(cottageReservations);
    }
}
