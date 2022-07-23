package com.bookingapplication.service;

import com.bookingapplication.dto.CottageReportDTO;
import com.bookingapplication.model.Cottage;
import com.bookingapplication.model.CottageReport;
import com.bookingapplication.repository.CottageReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CottageReportService {
    @Autowired
    private CottageReportRepository cottageReportRepository;
    @Autowired
    private CottageOwnerService cottageOwnerService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CottageService cottageService;

    public CottageReport createReport (CottageReportDTO report) {
        //da li je klijent imao rezervaciju u toj vikendici i da li je ta rezervacija prosla (vraca broj takvih rezervacija, posto ne znam da vratim boolean :D)
        if(cottageReportRepository.clientHasReservedCottage(report.getClientId(), report.getCottageId())==0){
            return null;
        }
        Cottage cottage = cottageService.findById(report.getCottageId());
        CottageReport cottageReport = new CottageReport(cottageOwnerService.findById(cottage.getCottageOwner().getId()), clientService.findById(report.getClientId()),
                report.getDescription(), report.isReportClient(), cottage);
        CottageReport saveCottage = save(cottageReport);
        return saveCottage;
    }

    public CottageReport save(CottageReport report){
        return cottageReportRepository.save(report);
    }
}