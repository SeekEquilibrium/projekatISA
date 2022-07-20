package com.bookingapplication.service;

import com.bookingapplication.dto.DefineCottageAvailabilityRequestDTO;
import com.bookingapplication.dto.DefineCottageAvailabilityResponseDTO;
import com.bookingapplication.model.AppointmentCottage;
import com.bookingapplication.model.AppointmentType;
import com.bookingapplication.model.Cottage;
import com.bookingapplication.repository.AppointmentCottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AppointmentCottageService {
    @Autowired
    private AppointmentCottageRepository appointmentCottageRepository;

    public DefineCottageAvailabilityResponseDTO DefineCottageAvailability (DefineCottageAvailabilityRequestDTO request, Cottage cottage){
        for(LocalDate date = request.getStartDate(); date.isBefore(request.getEndDate().plusDays(1)); date = date.plusDays(1)){
            AppointmentCottage appointment = new AppointmentCottage(date, request.isHasAction(), cottage, request.getPricePerDay(), AppointmentType.AVAILABLE);
            //proveriti da li postoji slobodan/zauzet termin tog datuma, da ne bi doslo do dupliranja istog datuma
            if(!appointmentCottageRepository.existsByDate(date)){
                save(appointment);
            }
        }
        return new DefineCottageAvailabilityResponseDTO(cottage.getId(), request.getPricePerDay(), request.isHasAction(), request.getStartDate(), request.getEndDate());
    }

    public DefineCottageAvailabilityResponseDTO DefineCottageAction (DefineCottageAvailabilityRequestDTO request, Cottage cottage){
        for(LocalDate date = request.getStartDate(); date.isBefore(request.getEndDate().plusDays(1)); date = date.plusDays(1)){
            //proveriti da li postoji slobodan/zauzet termin tog datuma, da ne bi doslo do dupliranja istog datuma
            if(!appointmentCottageRepository.existsByDate(date)){
                AppointmentCottage appointment = new AppointmentCottage(date, request.isHasAction(), cottage, request.getPricePerDay(), AppointmentType.AVAILABLE);
                save(appointment);
            }else{
                //izmena postojeceg termina, provera da li je taj termin vec na akciji (ako jeste, just skip)
                AppointmentCottage appointment = appointmentCottageRepository.findByDate(date);
                if(!appointment.isHasAction()){
                    appointment.setHasAction(true);
                    save(appointment);
                }
            }
        }
        return new DefineCottageAvailabilityResponseDTO(cottage.getId(), request.getPricePerDay(), request.isHasAction(), request.getStartDate(), request.getEndDate());
    }

    public AppointmentCottage save(AppointmentCottage appointmentCottage){
        return appointmentCottageRepository.save(appointmentCottage);
    }
}
