package com.bookingapplication.service;

import com.bookingapplication.dto.DefineBoatAvailabilityRequestDTO;
import com.bookingapplication.dto.DefineBoatAvailabilityResponseDTO;
import com.bookingapplication.dto.DefineCottageAvailabilityRequestDTO;
import com.bookingapplication.dto.DefineCottageAvailabilityResponseDTO;
import com.bookingapplication.model.*;
import com.bookingapplication.repository.AppointmentBoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AppointmentBoatService {
    @Autowired
    private AppointmentBoatRepository appointmentBoatRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BoatService boatService;

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

    public AppointmentBoat save(AppointmentBoat appointmentBoat){
        return appointmentBoatRepository.save(appointmentBoat);
    }

    public boolean existsByDate(LocalDate date) {
        return appointmentBoatRepository.existsByDate(date);
    }

    public AppointmentBoat findByDate(LocalDate date){
        return appointmentBoatRepository.findByDate(date);
    }


}
