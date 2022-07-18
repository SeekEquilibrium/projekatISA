package com.bookingapplication.service;

import com.bookingapplication.model.CottageOwner;
import com.bookingapplication.repository.CottageOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CottageOwnerService {
    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;

    public CottageOwner findCottageOwner(long Id){
        return cottageOwnerRepository.findById(Id);
    }
}
