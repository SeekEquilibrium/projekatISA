package com.bookingapplication.controller;

import com.bookingapplication.dto.BoatReportDTO;
import com.bookingapplication.dto.CottageReportDTO;
import com.bookingapplication.model.*;
import com.bookingapplication.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/report/boat")
public class BoatReportController {
    @Autowired
    private BoatService boatService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private BoatReportService boatReportService;

    @PostMapping("/report")
    @PreAuthorize("hasAuthority('BOAT_OWNER')")
    public ResponseEntity<BoatReportDTO> createReport(@Valid @RequestBody BoatReportDTO report) throws InterruptedException {
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        BoatOwner boatOwner = boatOwnerService.findById(userApp.getId());
        //Da li vikendica pripada gazdi koji salje zahtev
        if(boatService.ownerOwnsBoat(boatOwner.getId(), report.getBoatId())){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        //Da li klijent postoji
        if(!clientService.existsById(report.getClientId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        BoatReport response = boatReportService.createReport(report);
        if(response == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<BoatReportDTO>(report, HttpStatus.OK);
    }
}
