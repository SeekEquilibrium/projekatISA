package com.bookingapplication.controller;

import com.bookingapplication.dto.CottageReportDTO;
import com.bookingapplication.model.CottageOwner;
import com.bookingapplication.model.CottageReport;
import com.bookingapplication.model.UserApp;
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
@RequestMapping("/report/cottage")
public class CottageReportController {
    @Autowired
    private CottageService cottageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CottageOwnerService cottageOwnerService;
    @Autowired
    private CottageReportService cottageReportService;

    @PostMapping("/report")
    @PreAuthorize("hasAuthority('COTTAGE_OWNER')")
    public ResponseEntity<?> createReport(@Valid @RequestBody CottageReportDTO report){
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        CottageOwner cottageOwner = cottageOwnerService.findById(userApp.getId());
        //Da li vikendica pripada gazdi koji salje zahtev
        if(cottageService.ownerOwnsCottage(cottageOwner.getId(), report.getCottageId())){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        //Da li klijent postoji
        if(!clientService.existsById(report.getClientId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        CottageReport response = cottageReportService.createReport(report);
        if(response == null){
            return new ResponseEntity<String>("Client has never reserved this cottage.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("You have submitted report successfully.", HttpStatus.OK);
    }
}
