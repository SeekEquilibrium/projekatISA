package com.bookingapplication.service;

import com.bookingapplication.dto.DefineCottageAvailabilityRequestDTO;
import com.bookingapplication.dto.DefineCottageAvailabilityResponseDTO;
import com.bookingapplication.model.AppointmentCottage;
import com.bookingapplication.model.AppointmentType;
import com.bookingapplication.model.Cottage;
import com.bookingapplication.repository.AppointmentCottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentCottageService {
    @Autowired
    private AppointmentCottageRepository appointmentCottageRepository;

    public DefineCottageAvailabilityResponseDTO DefineCottageAvailability (DefineCottageAvailabilityRequestDTO request, Cottage cottage){

        for(LocalDateTime date = request.getStartDate(); date.isBefore(request.getEndDate()); date = date.plusDays(1)){
            AppointmentCottage appointment = new AppointmentCottage(date, false, cottage, request.getPricePerDay(), AppointmentType.AVAILABLE);
            //proveriti da li postoji appointemnt sa tim datumom
            save(appointment);
        }
        return new DefineCottageAvailabilityResponseDTO(cottage.getId(), request.getPricePerDay(), request.getStartDate(), request.getEndDate());
    }

    public AppointmentCottage save(AppointmentCottage appointmentCottage){
        return appointmentCottageRepository.save(appointmentCottage);
    }
}
