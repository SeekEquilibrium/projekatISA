package com.bookingapplication.service;

import com.bookingapplication.dto.EditBoatRequestDTO;
import com.bookingapplication.dto.RegisterBoatRequestDTO;
import com.bookingapplication.model.*;
import com.bookingapplication.repository.BoatRepository;
import com.bookingapplication.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class BoatService {
    @Autowired
    private BoatRepository boatRepository;

    public Boolean existsByName(String name) { return boatRepository.existsByName(name); }
    public Boolean existsById(long id) { return boatRepository.existsById(id); }
    public Boat findByName(String name) { return boatRepository.findByName(name); }
    public Boat findById(long id) { return boatRepository.findById(id); }
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

    public Boat editBoat(EditBoatRequestDTO requestDTO) throws IOException {
        Boat boat = boatRepository.findById(requestDTO.getId());
        boat.setName(requestDTO.getName());
        boat.setAddress(requestDTO.getAddress());
        boat.setLongitude(requestDTO.getLongitude());
        boat.setLatitude(requestDTO.getLatitude());
        boat.setDescription(requestDTO.getDescription());
        boat.setRules(requestDTO.getRules());

        Set<BoatImage> boatImages = boat.getBoatImages();
        if(requestDTO.getDeletedImages()!=null){
            for(String path : requestDTO.getDeletedImages()){
                boatImages.removeIf(image->image.getPath().equals(path));
            }
        }

        if(requestDTO.getFiles()!=null){
            for(MultipartFile image : requestDTO.getFiles()){
                String fileName = boat.getName() + "-" + UUID.randomUUID() + ".png";
                FileUploadUtil.saveFile(FileUploadUtil.getImageFolder("boats"), fileName, image);
                BoatImage boatImage = new BoatImage(fileName, boat);
                boatImages.add(boatImage);
            }
        }
        boat.setBoatImages(boatImages);
        return boatRepository.save(boat);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Boat findByIdPess(long id){
        return boatRepository.findByIdPess(id);
    }

    public boolean ownerOwnsBoat(long boatOwnerId, long boatId){
        //odraditi ovaj deo preko query
        return findById(boatId).getBoatOwner().equals(boatOwnerId);
    }

    public ArrayList<Boat> findByBoatOwnerId(long cottageOwnerId){
        return boatRepository.findByBoatOwnerId(cottageOwnerId);
    }
}
