package com.bookingapplication.controller;

import com.bookingapplication.dto.CottageOwnerReservationRequestDTO;
import com.bookingapplication.dto.CottageOwnerReservationResponseDTO;
import com.bookingapplication.dto.DefineCottageAvailabilityRequestDTO;
import com.bookingapplication.dto.DefineCottageAvailabilityResponseDTO;
import com.bookingapplication.model.Cottage;
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
@RequestMapping("/appointment/cottage")
public class CottageAppointmentController {
    @Autowired
    private CottageService cottageService;
    @Autowired
    private UserService userService;
    @Autowired
    private CottageOwnerService cottageOwnerService;
    @Autowired
    private DateValidation dateValidation;
    @Autowired
    private AppointmentCottageService appointmentCottageService;
    @Autowired
    private ClientService clientService;

    @PostMapping("/defineAvailability")
    @PreAuthorize("hasAuthority('COTTAGE_OWNER')")
    public ResponseEntity<DefineCottageAvailabilityResponseDTO> defineAvailabilityOrActions(@Valid @RequestBody DefineCottageAvailabilityRequestDTO request){
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        CottageOwner cottageOwner = cottageOwnerService.findById(userApp.getId());
        //Da li vikendica postoji
        if(!cottageService.existsById(request.getCottageId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li vikendica pripada gazdi koji salje zahtev
        if(cottageService.ownerOwnsCottage(cottageOwner.getId(), request.getCottageId())){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        //Da li je  datum u buducnosti, da li je pocetak pre kraja
        if(dateValidation.isDateBeforeToday(request.getStartDate()) ||
                !dateValidation.isFirstBeforeSecondDate(request.getStartDate(), request.getEndDate())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        DefineCottageAvailabilityResponseDTO response = null;
        //Da li je definisanje akcija ili slobodnih termina
        if(request.isHasAction()){
            response = appointmentCottageService.DefineCottageAction(request);
        }else{
            response = appointmentCottageService.DefineCottageAvailability(request);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/ownerCreateReservation")
    @PreAuthorize("hasAuthority('COTTAGE_OWNER')")
    public ResponseEntity<CottageOwnerReservationResponseDTO> cottageOwnerReservationRequest (@Valid @RequestBody CottageOwnerReservationRequestDTO request) {
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        CottageOwner cottageOwner = cottageOwnerService.findById(userApp.getId());
        //Da li vikendica postoji
        if(!cottageService.existsById(request.getCottageId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li vikendica pripada gazdi koji salje zahtev
        if(cottageService.ownerOwnsCottage(request.getClientId(), request.getCottageId())){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        //Da li klijent postoji
        if(!clientService.existsById(request.getClientId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li je  datum u buducnosti, da li je pocetak pre kraja
        if(dateValidation.isDateBeforeToday(request.getStartDate()) ||
                !dateValidation.isFirstBeforeSecondDate(request.getStartDate(), request.getEndDate())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        CottageOwnerReservationResponseDTO response = appointmentCottageService.CreateReservationForClient(request);
        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
