package com.bookingapplication.service;

import com.bookingapplication.dto.RegisterBoatRequestDTO;
import com.bookingapplication.model.Boat;
import com.bookingapplication.model.BoatImage;
import com.bookingapplication.model.BoatOwner;
import com.bookingapplication.model.CottageImage;
import com.bookingapplication.repository.BoatRepository;
import com.bookingapplication.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class BoatService {
    @Autowired
    private BoatRepository boatRepository;

    public Boolean existsByName(String name) { return boatRepository.existsByName(name); }

    public Boat registerBoat(RegisterBoatRequestDTO requestDTO, BoatOwner boatOwner) throws IOException {
        Boat boat = new Boat(
                requestDTO.getName(),
                requestDTO.getAddress(),
                requestDTO.getLongitude(),
                requestDTO.getLatitude(),
                requestDTO.getDescription(),
                requestDTO.getRules(),
                boatOwner
        );
        Set<BoatImage> boatImages = new HashSet<>();
        if(requestDTO.getFiles()!=null){
            for(MultipartFile image : requestDTO.getFiles()){
                String fileName = boat.getName() + "_" + UUID.randomUUID() + ".png";
                FileUploadUtil.saveFile(FileUploadUtil.getImageFolder("boats"), fileName, image);
                BoatImage boatImage = new BoatImage(fileName, boat);
                boatImages.add(boatImage);
            }
        }
        boat.setBoatImages(boatImages);
        boatRepository.save(boat);
        return boat;
    }
}
