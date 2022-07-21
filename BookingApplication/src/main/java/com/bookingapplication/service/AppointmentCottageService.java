package com.bookingapplication.service;

import com.bookingapplication.dto.CottageOwnerReservationRequestDTO;
import com.bookingapplication.dto.CottageOwnerReservationResponseDTO;
import com.bookingapplication.dto.DefineCottageAvailabilityRequestDTO;
import com.bookingapplication.dto.DefineCottageAvailabilityResponseDTO;
import com.bookingapplication.model.AppointmentCottage;
import com.bookingapplication.model.AppointmentType;
import com.bookingapplication.model.Client;
import com.bookingapplication.model.Cottage;
import com.bookingapplication.repository.AppointmentCottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
                    save(appointment);
                }
            }
        }
        return new DefineCottageAvailabilityResponseDTO(cottage.getId(), request.getPricePerDay(), request.isHasAction(), request.getStartDate(), request.getEndDate());
    }

    public CottageOwnerReservationResponseDTO CreateReservationForClient (CottageOwnerReservationRequestDTO request){
        if(!existsByDate(request.getStartDate()) || !existsByDate(request.getEndDate())){
            return null;
        }
        Cottage cottage = cottageService.findById(request.getCottageId());
        Client client = clientService.findById(request.getClientId());
        for(LocalDate date = request.getStartDate(); date.isBefore(request.getEndDate().plusDays(1)); date = date.plusDays(1)){
            AppointmentCottage reservation = findByDate(date);
            if(reservation.getType() != AppointmentType.AVAILABLE){
                return null;
            }
            reservation.setClient(client);
            reservation.setType(AppointmentType.RESERVED);
            save(reservation);
        }
        return new CottageOwnerReservationResponseDTO(cottage.getId(), client.getId(), request.getStartDate(), request.getEndDate());
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
