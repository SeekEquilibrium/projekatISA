package com.bookingapplication.service;

import com.bookingapplication.dto.CottageReportDTO;
import com.bookingapplication.model.Cottage;
import com.bookingapplication.model.CottageReport;
import com.bookingapplication.repository.CottageReportRepository;
import com.bookingapplication.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    @Autowired
    private CottageRepository cottageRepository;

    @Transactional(readOnly = false)
    public CottageReport createReport (CottageReportDTO report) throws InterruptedException {
        //da li je klijent imao rezervaciju u toj vikendici i da li je ta rezervacija prosla (vraca broj takvih rezervacija, posto ne znam da vratim boolean :D)
        if(cottageReportRepository.clientHasReservedCottage(report.getClientId(), report.getCottageId())==0){
            return null;
        }
        Cottage cottage = cottageRepository.findByIdPess(report.getCottageId());
        CottageReport cottageReport = new CottageReport(cottageOwnerService.findById(cottage.getCottageOwner().getId()), clientService.findById(report.getClientId()),
                report.getDescription(), LocalDateTime.now(), report.isReportClient(), report.isDidNotShowUp(), cottage);
//        Thread.sleep(8000);
        CottageReport saveCottage = save(cottageReport);
        return saveCottage;
    }

    @Transactional
    public CottageReport save(CottageReport report){
        return cottageReportRepository.save(report);
    }
}
