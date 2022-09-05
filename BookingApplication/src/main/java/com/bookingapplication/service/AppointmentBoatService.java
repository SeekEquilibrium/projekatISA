package com.bookingapplication.service;

import com.bookingapplication.dto.BoatReservationResponseDTO;
import com.bookingapplication.dto.DefineBoatAvailabilityRequestDTO;
import com.bookingapplication.dto.DefineBoatAvailabilityResponseDTO;
import com.bookingapplication.model.*;
import com.bookingapplication.repository.AppointmentBoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentBoatService {
    @Autowired
    private AppointmentBoatRepository appointmentBoatRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BoatService boatService;
    @Autowired
    private BoatReservationsService boatReservationsService;

    public DefineBoatAvailabilityResponseDTO DefineBoatAvailability (DefineBoatAvailabilityRequestDTO request){
        Boat boat = boatService.findById(request.getBoatId());
        for(LocalDate date = request.getStartDate(); date.isBefore(request.getEndDate().plusDays(1)); date = date.plusDays(1)){
            AppointmentBoat appointment = new AppointmentBoat(date, request.isHasAction(), boat, request.getPricePerDay(), AppointmentType.AVAILABLE);
            //proveriti da li postoji slobodan/zauzet termin tog datuma, da ne bi doslo do dupliranja istog datuma
            if(!existsByDate(date)){
                save(appointment);
            }
        }
        return new DefineBoatAvailabilityResponseDTO(boat.getId(), request.getPricePerDay(), request.isHasAction(), request.getStartDate(), request.getEndDate());
    }

    public DefineBoatAvailabilityResponseDTO DefineBoatAction (DefineBoatAvailabilityRequestDTO request){
        Boat boat = boatService.findById(request.getBoatId());
        for(LocalDate date = request.getStartDate(); date.isBefore(request.getEndDate().plusDays(1)); date = date.plusDays(1)){
            //proveriti da li postoji slobodan/zauzet termin tog datuma, da ne bi doslo do dupliranja istog datuma
            if(!existsByDate(date)){
                AppointmentBoat appointment = new AppointmentBoat(date, request.isHasAction(), boat, request.getPricePerDay(), AppointmentType.AVAILABLE);
                save(appointment);
            }else{
                //izmena postojeceg termina, provera da li je taj termin vec na akciji (ako jeste, just skip)
                AppointmentBoat appointment = findByDate(date);
                if(!appointment.isHasAction()){
                    appointment.setHasAction(true);
                    appointment.setPricePerDay(request.getPricePerDay());
                    save(appointment);
                }
            }
        }
        return new DefineBoatAvailabilityResponseDTO(boat.getId(), request.getPricePerDay(), request.isHasAction(), request.getStartDate(), request.getEndDate());
    }

    //Vlasnik vikendice kreira rezervaciju za klijenta
    public BoatReservationResponseDTO CreateReservationForClient (long clientId , long boatId, LocalDate startTime , LocalDate endTime){
        //Ako pokusa da rezervise u terminima kada nije definisana dostupnos vikendice
        if(!existsByDate(startTime)|| !existsByDate(endTime)){
            return null;
        }
        Boat boat = boatService.findById(boatId);
        Client client = clientService.findById(clientId);
        for(LocalDate date = startTime; date.isBefore(endTime.plusDays(1)); date = date.plusDays(1)){
            AppointmentBoat reservation = findByDate(date);
            if(reservation.getType() != AppointmentType.AVAILABLE){
                return null;
            }
            reservation.setClient(client);
            reservation.setType(AppointmentType.RESERVED);
            save(reservation);
        }
        boatReservationsService.save(new BoatReservations(startTime, endTime, ReservationStatus.PENDING, boat, client));
        return new BoatReservationResponseDTO(boatId, clientId, startTime, endTime);
    }

    public List<?> getRevanueByYears(long boatId){
        return appointmentBoatRepository.getRevanueByYears(boatId);
    }

    public List<?> getRevanueForLastYear(long boatId){
        return appointmentBoatRepository.getRevanueForLastYear(boatId);
    }

    public List<?> getReservationDaysByYears(long boatId){
        return appointmentBoatRepository.getReservationDaysByYears(boatId);
    }

    public List<?> getReservationDaysForLastYear(long boatId){
        return appointmentBoatRepository.getReservationDaysForLastYear(boatId);
    }

    public ArrayList<AppointmentBoat> getBoatReservations(long boatId){
        return appointmentBoatRepository.getBoatReservations(boatId);
    }

    public ArrayList<AppointmentBoat> GetBoatAvailability(long boatId){
        return appointmentBoatRepository.getAvailabeAppointments(boatId);
    }

    public ArrayList<AppointmentBoat> GetBoatActions(long boatId){
        return appointmentBoatRepository.getActionAppointments(boatId);
    }

    public AppointmentBoat save(AppointmentBoat appointmentBoat){
        return appointmentBoatRepository.save(appointmentBoat);
    }
    public ArrayList<AppointmentBoat> GetBoatAvailabilityAndReservations(long boatId){
        return appointmentBoatRepository.getAvailabeAppointmentsAndReservations(boatId);
    }

    public boolean existsByDate(LocalDate date) {
        return appointmentBoatRepository.existsByDate(date);
    }

    public AppointmentBoat findByDate(LocalDate date){
        return appointmentBoatRepository.findByDate(date);
    }


}
