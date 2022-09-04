package com.bookingapplication.service;

import com.bookingapplication.dto.BoatReportDTO;
import com.bookingapplication.dto.CottageReportDTO;
import com.bookingapplication.model.Boat;
import com.bookingapplication.model.BoatReport;
import com.bookingapplication.model.Cottage;
import com.bookingapplication.model.CottageReport;
import com.bookingapplication.repository.BoatReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoatReportService {
    @Autowired
    private BoatReportRepository boatReportRepository;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BoatService boatService;

    public BoatReport createReport (BoatReportDTO report) {
        //da li je klijent imao rezervaciju u toj vikendici i da li je ta rezervacija prosla (vraca broj takvih rezervacija, posto ne znam da vratim boolean :D)
        if(boatReportRepository.clientHasReservedBoat(report.getClientId(), report.getBoatId())==0){
            return null;
        }
        Boat boat = boatService.findById(report.getBoatId());
        BoatReport boatReport = new BoatReport(boatOwnerService.findById(boat.getBoatOwner().getId()), clientService.findById(report.getClientId()),
                report.getDescription(), LocalDateTime.now(), report.isReportClient(), report.isDidNotShowUp(), boat);
        BoatReport saveBoat = save(boatReport);
        return saveBoat;
    }

    public BoatReport save(BoatReport report){
        return boatReportRepository.save(report);
    }
}
