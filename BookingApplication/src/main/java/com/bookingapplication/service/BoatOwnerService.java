package com.bookingapplication.service;

import com.bookingapplication.model.BoatOwner;
import com.bookingapplication.model.CottageOwner;
import com.bookingapplication.repository.BoatOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoatOwnerService {
    @Autowired
    private BoatOwnerRepository boatOwnerRepository;

    public BoatOwner findById(long Id){
        return boatOwnerRepository.findById(Id);
    }
}
