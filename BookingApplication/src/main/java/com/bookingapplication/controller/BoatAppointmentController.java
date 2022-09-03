package com.bookingapplication.controller;

import com.bookingapplication.dto.DefineBoatAvailabilityRequestDTO;
import com.bookingapplication.dto.DefineBoatAvailabilityResponseDTO;
import com.bookingapplication.dto.DefineCottageAvailabilityRequestDTO;
import com.bookingapplication.dto.DefineCottageAvailabilityResponseDTO;
import com.bookingapplication.model.BoatOwner;
import com.bookingapplication.model.CottageOwner;
import com.bookingapplication.model.UserApp;
import com.bookingapplication.service.*;
import com.bookingapplication.validation.DateValidation;
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
@RequestMapping("/appointment/boat")
public class BoatAppointmentController {
    @Autowired
    private BoatService boatService;
    @Autowired
    private UserService userService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private DateValidation dateValidation;
    @Autowired
    private AppointmentBoatService appointmentBoatService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BoatReservationsService boatReservationsService;

    @PostMapping("/defineAvailability")
    @PreAuthorize("hasAuthority('BOAT_OWNER')")
    public ResponseEntity<DefineBoatAvailabilityResponseDTO> defineAvailabilityOrActions(@Valid @RequestBody DefineBoatAvailabilityRequestDTO request){
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        BoatOwner boatOwner = boatOwnerService.findById(userApp.getId());
        //Da li vikendica postoji
        if(!boatService.existsById(request.getBoatId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li vikendica pripada gazdi koji salje zahtev
        if(boatService.ownerOwnsBoat(boatOwner.getId(), request.getBoatId())){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        //Da li je  datum u buducnosti, da li je pocetak pre kraja
        if(dateValidation.isDateBeforeToday(request.getStartDate()) ||
                !dateValidation.isFirstBeforeSecondDate(request.getStartDate(), request.getEndDate())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        DefineBoatAvailabilityResponseDTO response = null;
        //Da li je definisanje akcija ili slobodnih termina
        if(request.isHasAction()){
            response = appointmentBoatService.DefineBoatAction(request);
        }else{
            response = appointmentBoatService.DefineBoatAvailability(request);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
