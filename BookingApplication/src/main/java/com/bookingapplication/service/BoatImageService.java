package com.bookingapplication.service;

import com.bookingapplication.repository.BoatImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoatImageService {
    @Autowired
    private BoatImageRepository boatImageRepository;

    public ArrayList<String> findImagePathsByBoatId(long id){
        return boatImageRepository.findImagePathsByBoatId(id);
    }
}
