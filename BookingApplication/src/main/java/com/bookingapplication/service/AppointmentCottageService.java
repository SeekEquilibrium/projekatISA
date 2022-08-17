package com.bookingapplication.service;

import com.bookingapplication.dto.*;
import com.bookingapplication.model.AppointmentCottage;
import com.bookingapplication.model.AppointmentType;
import com.bookingapplication.model.Client;
import com.bookingapplication.model.Cottage;
import com.bookingapplication.repository.AppointmentCottageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentCottageService {
    @Autowired
    private AppointmentCottageRepository appointmentCottageRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CottageService cottageService;

    public DefineCottageAvailabilityResponseDTO DefineCottageAvailability (DefineCottageAvailabilityRequestDTO request){
        Cottage cottage = cottageService.findById(request.getCottageId());
        for(LocalDate date = request.getStartDate(); date.isBefore(request.getEndDate().plusDays(1)); date = date.plusDays(1)){
            AppointmentCottage appointment = new AppointmentCottage(date, request.isHasAction(), cottage, request.getPricePerDay(), AppointmentType.AVAILABLE);
            //proveriti da li postoji slobodan/zauzet termin tog datuma, da ne bi doslo do dupliranja istog datuma
            if(!existsByDate(date)){
                save(appointment);
            }
        }
        return new DefineCottageAvailabilityResponseDTO(cottage.getId(), request.getPricePerDay(), request.isHasAction(), request.getStartDate(), request.getEndDate());
    }

    public DefineCottageAvailabilityResponseDTO DefineCottageAction (DefineCottageAvailabilityRequestDTO request){
        Cottage cottage = cottageService.findById(request.getCottageId());
        for(LocalDate date = request.getStartDate(); date.isBefore(request.getEndDate().plusDays(1)); date = date.plusDays(1)){
            //proveriti da li postoji slobodan/zauzet termin tog datuma, da ne bi doslo do dupliranja istog datuma
            if(!existsByDate(date)){
                AppointmentCottage appointment = new AppointmentCottage(date, request.isHasAction(), cottage, request.getPricePerDay(), AppointmentType.AVAILABLE);
                save(appointment);
            }else{
                //izmena postojeceg termina, provera da li je taj termin vec na akciji (ako jeste, just skip)
                AppointmentCottage appointment = findByDate(date);
                if(!appointment.isHasAction()){
                    appointment.setHasAction(true);
                    appointment.setPricePerDay(request.getPricePerDay());
                    save(appointment);
                }
            }
        }
        return new DefineCottageAvailabilityResponseDTO(cottage.getId(), request.getPricePerDay(), request.isHasAction(), request.getStartDate(), request.getEndDate());
    }
    //Vlasnik vikendice kreira rezervaciju za klijenta
    public CottageReservationResponseDTO CreateReservationForClient (long clientId ,long cottageId,LocalDate startTime , LocalDate endTime){
        //Ako pokusa da rezervise u terminima kada nije definisana dostupnos vikendice
        if(!existsByDate(startTime)|| !existsByDate(endTime)){
            return null;
        }
        Cottage cottage = cottageService.findById(cottageId);
        Client client = clientService.findById(clientId);
        for(LocalDate date = startTime; date.isBefore(endTime.plusDays(1)); date = date.plusDays(1)){
            AppointmentCottage reservation = findByDate(date);
            if(reservation.getType() != AppointmentType.AVAILABLE){
                return null;
            }
            reservation.setClient(client);
            reservation.setType(AppointmentType.RESERVED);
            save(reservation);
        }
        return new CottageReservationResponseDTO(cottageId, clientId, startTime, endTime);
    }
    public boolean CheckActionAvailability (long cottageId, LocalDate startTime , LocalDate endTime){
        if(!existsByDate(startTime)|| !existsByDate(endTime)){
            return false;
        }
        Cottage cottage = cottageService.findById(cottageId);
        for(LocalDate date = startTime; date.isBefore(endTime.plusDays(1)); date = date.plusDays(1)){
            AppointmentCottage reservation = findByDate(date);
            if(reservation.getType() != AppointmentType.AVAILABLE){
               if(reservation.isHasAction() != true){
                   return false;
               }
            }
        }
        return  true;
    }

    public ArrayList<AppointmentCottage> GetCottageAvailability(long cottageId){
        return appointmentCottageRepository.getAvailabeAppointmentsAndReservations(cottageId);
    }

    public ArrayList<AppointmentCottage> GetCottageActions(long cottageId){
        return appointmentCottageRepository.getActionAppointments(cottageId);
    }

    public AppointmentCottage save(AppointmentCottage appointmentCottage){
        return appointmentCottageRepository.save(appointmentCottage);
    }

    public boolean existsByDate(LocalDate date) {
        return appointmentCottageRepository.existsByDate(date);
    }

    public AppointmentCottage findByDate(LocalDate date){
        return appointmentCottageRepository.findByDate(date);
    }
}
