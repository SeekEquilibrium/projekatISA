package com.bookingapplication.service;

import com.bookingapplication.dto.*;
import com.bookingapplication.model.*;
import com.bookingapplication.repository.AppointmentCottageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    @Autowired
    private CottageReservationsService cottageReservationsService;

    public DefineCottageAvailabilityResponseDTO DefineCottageAvailability (DefineCottageAvailabilityRequestDTO request){
        Cottage cottage = cottageService.findById(request.getCottageId());
        for(LocalDate date = request.getStartDate(); date.isBefore(request.getEndDate().plusDays(1)); date = date.plusDays(1)){
            AppointmentCottage appointment = new AppointmentCottage(date, request.isHasAction(), cottage, request.getPricePerDay(), AppointmentType.AVAILABLE);
            //proveriti da li postoji slobodan/zauzet termin tog datuma, da ne bi doslo do dupliranja istog datuma
            if(!existsByDateAndCottageId(date, cottage.getId())){
                save(appointment);
            }
        }
        return new DefineCottageAvailabilityResponseDTO(cottage.getId(), request.getPricePerDay(), request.isHasAction(), request.getStartDate(), request.getEndDate());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public DefineCottageAvailabilityResponseDTO DefineCottageAction (DefineCottageAvailabilityRequestDTO request) throws InterruptedException {
        Cottage cottage = cottageService.findById(request.getCottageId());
        ArrayList<AppointmentCottage> saveReservations = new ArrayList<>();
        for(LocalDate date = request.getStartDate(); date.isBefore(request.getEndDate().plusDays(1)); date = date.plusDays(1)){
            //proveriti da li postoji slobodan/zauzet termin tog datuma, da ne bi doslo do dupliranja istog datuma
            if(!existsByDateAndCottageId(date, cottage.getId())){
                AppointmentCottage appointment = new AppointmentCottage(date, request.isHasAction(), cottage, request.getPricePerDay(), AppointmentType.AVAILABLE);
//                save(appointment);
                saveReservations.add(appointment);
            }else{
                //izmena postojeceg termina, provera da li je taj termin vec na akciji (ako jeste, just skip)
                AppointmentCottage appointment = findByDate(date, cottage.getId());
                if(!appointment.isHasAction()){
                    appointment.setHasAction(true);
                    appointment.setPricePerDay(request.getPricePerDay());
//                    save(appointment);
                    saveReservations.add(appointment);

                }
            }
        }
//        Thread.sleep(10000);
        saveAll(saveReservations);
        return new DefineCottageAvailabilityResponseDTO(cottage.getId(), request.getPricePerDay(), request.isHasAction(), request.getStartDate(), request.getEndDate());
    }
    //Vlasnik vikendice kreira rezervaciju za klijenta
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public CottageReservationResponseDTO CreateReservationForClient (long clientId ,long cottageId,LocalDate startTime , LocalDate endTime) throws InterruptedException {
        //Ako pokusa da rezervise u terminima kada nije definisana dostupnos vikendice
        if(!existsByDateAndCottageId(startTime,cottageId)|| !existsByDateAndCottageId(endTime,cottageId)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
        Cottage cottage = cottageService.findById(cottageId);
        Client client = clientService.findById(clientId);
        ArrayList<AppointmentCottage> saveReservations = new ArrayList<>();
        for(LocalDate date = startTime; date.isBefore(endTime.plusDays(1)); date = date.plusDays(1)){
            AppointmentCottage reservation = findByDate(date,cottageId);
            if(reservation == null || reservation.getType() != AppointmentType.AVAILABLE){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return null;
            }
            reservation.setClient(client);
            reservation.setType(AppointmentType.RESERVED);
            saveReservations.add(reservation);
        }
//        Thread.sleep(15000);
        cottageReservationsService.save(new CottageReservations(startTime, endTime, ReservationStatus.PENDING, cottage, client));
        saveAll(saveReservations);

        return new CottageReservationResponseDTO(cottageId, clientId, startTime, endTime);
    }

    public boolean CheckActionAvailability (long cottageId, LocalDate startTime , LocalDate endTime){
        if(!existsByDateAndCottageId(startTime, cottageId)|| !existsByDateAndCottageId(endTime,cottageId)){
            return false;
        }
        Cottage cottage = cottageService.findById(cottageId);
        for(LocalDate date = startTime; date.isBefore(endTime.plusDays(1)); date = date.plusDays(1)){
            AppointmentCottage reservation = findByDate(date, cottageId);
            if(reservation.getType() != AppointmentType.AVAILABLE){
               if(reservation.isHasAction() != true){
                   return false;
               }
            }
        }
        return  true;
    }

    public boolean cottageHasFutureReservations(long id){
        int num = appointmentCottageRepository.cottageHasFutureReservations(id);
        if(num==0){
            return false;
        }
        return true;
    }

    public List<?> getRevanueByYears(long cottageId){
        return appointmentCottageRepository.getRevanueByYears(cottageId);
    }

    public List<?> getRevanueForLastYear(long cottageId){
        return appointmentCottageRepository.getRevanueForLastYear(cottageId);
    }

    public List<?> getReservationDaysByYears(long cottageId){
        return appointmentCottageRepository.getReservationDaysByYears(cottageId);
    }

    public List<?> getReservationDaysForLastYear(long cottageId){
        return appointmentCottageRepository.getReservationDaysForLastYear(cottageId);
    }

    public ArrayList<AppointmentCottage> getCottageReservations(long cottageId){
        return appointmentCottageRepository.getCottageReservations(cottageId);
    }

    public ArrayList<AppointmentCottage> GetCottageAvailability(long cottageId){
        return appointmentCottageRepository.getAvailabeAppointments(cottageId);
    }
    public ArrayList<AppointmentCottage> GetCottageAvailabilityAndReservations(long cottageId){
        return appointmentCottageRepository.getAvailabeAppointmentsAndReservations(cottageId);
    }

    public ArrayList<AppointmentCottage> GetCottageActions(long cottageId){
        return appointmentCottageRepository.getActionAppointments(cottageId);
    }

    public AppointmentCottage save(AppointmentCottage appointmentCottage){
        return appointmentCottageRepository.save(appointmentCottage);
    }

    public List<AppointmentCottage> saveAll(ArrayList<AppointmentCottage> appointmentCottageList){
        return appointmentCottageRepository.saveAll(appointmentCottageList);
    }

    public boolean existsByDateAndCottageId(LocalDate date, long cottageId) {
        return appointmentCottageRepository.existsByDateAndCottageId(date, cottageId);
    }

    public AppointmentCottage findByDate(LocalDate date, long cottageId){
        return appointmentCottageRepository.findByDate(date, cottageId);
    }
}
