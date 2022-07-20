package com.bookingapplication.controller;

import com.bookingapplication.dto.DefineCottageAvailabilityRequestDTO;
import com.bookingapplication.dto.DefineCottageAvailabilityResponseDTO;
import com.bookingapplication.model.Cottage;
import com.bookingapplication.model.CottageOwner;
import com.bookingapplication.model.UserApp;
import com.bookingapplication.service.AppointmentCottageService;
import com.bookingapplication.service.CottageOwnerService;
import com.bookingapplication.service.CottageService;
import com.bookingapplication.service.UserService;
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

    @PostMapping("/defineAvailability")
    @PreAuthorize("hasAuthority('COTTAGE_OWNER')")
    public ResponseEntity<DefineCottageAvailabilityResponseDTO> defineAvailabilityOrActions(@Valid @RequestBody DefineCottageAvailabilityRequestDTO request){
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        CottageOwner cottageOwner = cottageOwnerService.findCottageOwner(userApp.getId());
        //Da li vikendica postoji
        if(!cottageService.cottageExistsById(request.getCottageId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li vikendica pripada gazdi koji salje zahtev
        Cottage cottage = cottageService.findCottageById(request.getCottageId());
        if(cottage.getCottageOwner().getId() != cottageOwner.getId()){
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
            response = appointmentCottageService.DefineCottageAction(request, cottage);
        }else{
            response = appointmentCottageService.DefineCottageAvailability(request, cottage);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
