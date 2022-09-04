package com.bookingapplication.controller;

import com.bookingapplication.dto.*;
import com.bookingapplication.dto.RevanueAndVisists.StatisticsDTO;
import com.bookingapplication.dto.reservationInfo.ReservationDTO;
import com.bookingapplication.model.*;
import com.bookingapplication.service.*;
import com.bookingapplication.validation.DateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointment/boat")
public class BoatAppointmentController {
    @Autowired
    private BoatService boatService;
    @Autowired
    private UserService userService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private DateValidation dateValidation;
    @Autowired
    private AppointmentBoatService appointmentBoatService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BoatReservationsService boatReservationsService;

    @PostMapping("/defineAvailability")
    @PreAuthorize("hasAuthority('BOAT_OWNER')")
    public ResponseEntity<DefineBoatAvailabilityResponseDTO> defineAvailabilityOrActions(@Valid @RequestBody DefineBoatAvailabilityRequestDTO request){
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        BoatOwner boatOwner = boatOwnerService.findById(userApp.getId());
        //Da li vikendica postoji
        if(!boatService.existsById(request.getBoatId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li vikendica pripada gazdi koji salje zahtev
        if(boatService.ownerOwnsBoat(boatOwner.getId(), request.getBoatId())){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        //Da li je  datum u buducnosti, da li je pocetak pre kraja
        if(dateValidation.isDateBeforeToday(request.getStartDate()) ||
                !dateValidation.isFirstBeforeSecondDate(request.getStartDate(), request.getEndDate())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        DefineBoatAvailabilityResponseDTO response = null;
        //Da li je definisanje akcija ili slobodnih termina
        if(request.isHasAction()){
            response = appointmentBoatService.DefineBoatAction(request);
        }else{
            response = appointmentBoatService.DefineBoatAvailability(request);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getBoatAvailabilityAndReservations/{boatId}")
    @PreAuthorize("hasAuthority('BOAT_OWNER')")
    public ResponseEntity<ArrayList<BoatAvailabilityDTO>> getBoatAvailabilityAndReservations(@PathVariable long boatId){
        if(!boatService.existsById(boatId)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        ArrayList<AppointmentBoat> list = appointmentBoatService.GetBoatAvailabilityAndReservations(boatId);
        ArrayList<BoatAvailabilityDTO> listDTO = new ArrayList<>();
        for(AppointmentBoat a : list){
            BoatAvailabilityDTO boatAvailabilityDTO = new BoatAvailabilityDTO(a);
            listDTO.add(boatAvailabilityDTO);
        }
        return new ResponseEntity<ArrayList<BoatAvailabilityDTO>>(listDTO, HttpStatus.OK);
    }

    @GetMapping("/getBoatAvailability/{boatId}")
    public ResponseEntity<ArrayList<BoatAvailabilityDTO>> getBoatAvailability(@PathVariable long boatId){
        if(!boatService.existsById(boatId)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        ArrayList<AppointmentBoat> list = appointmentBoatService.GetBoatAvailability(boatId);
        ArrayList<BoatAvailabilityDTO> listDTO = new ArrayList<>();
        for(AppointmentBoat a : list){
            BoatAvailabilityDTO boatAvailabilityDTO = new BoatAvailabilityDTO(a);
            listDTO.add(boatAvailabilityDTO);
        }
        return new ResponseEntity<ArrayList<BoatAvailabilityDTO>>(listDTO, HttpStatus.OK);
    }

    @GetMapping("/getBoatActions/{boatId}")
    @PreAuthorize("hasAuthority('BOAT_OWNER')")
    public ResponseEntity<ArrayList<BoatAvailabilityDTO>> getBoatActions(@PathVariable long boatId){
        if(!boatService.existsById(boatId)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        ArrayList<AppointmentBoat> list = appointmentBoatService.GetBoatActions(boatId);
        ArrayList<BoatAvailabilityDTO> listDTO = new ArrayList<>();
        for(AppointmentBoat a : list){
            BoatAvailabilityDTO boatAvailabilityDTO = new BoatAvailabilityDTO(a);
            listDTO.add(boatAvailabilityDTO);
        }
        return new ResponseEntity<ArrayList<BoatAvailabilityDTO>>(listDTO, HttpStatus.OK);
    }

    @PostMapping("/ownerCreateReservation")
    @PreAuthorize("hasAuthority('BOAT_OWNER')")
    public ResponseEntity<BoatReservationResponseDTO> boatOwnerReservationRequest (@Valid @RequestBody BoatOwnerReservationRequestDTO request) {
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        BoatOwner boatOwner = boatOwnerService.findById(userApp.getId());
        //Da li vikendica postoji
        if(!boatService.existsById(request.getBoatId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li vikendica pripada gazdi koji salje zahtev
        if(boatService.ownerOwnsBoat(userApp.getId(), request.getBoatId())){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        //Da li klijent postoji
        if(!clientService.existsById(request.getClientId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li je  datum u buducnosti, da li je pocetak pre kraja
        if(dateValidation.isDateBeforeToday(request.getStartDate()) ||
                !dateValidation.isFirstBeforeSecondDate(request.getStartDate(), request.getEndDate())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        BoatReservationResponseDTO response = appointmentBoatService.CreateReservationForClient(
                request.getClientId(), request.getBoatId(),request.getStartDate(),request.getEndDate());
        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/boatReservations/{boatId}")
    @PreAuthorize("hasAuthority('BOAT_OWNER')")
    public ResponseEntity<ArrayList<ReservationDTO>> boatReservations(@PathVariable long boatId){
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        //Da li vikendica postoji
        if(!boatService.existsById(boatId)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li vikendica pripada gazdi koji salje zahtev
        if(boatService.ownerOwnsBoat(userApp.getId(), boatId)){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        ArrayList<BoatReservations> reservations = boatReservationsService.getBoatReservations(boatId);
        ArrayList<ReservationDTO> reservationsDTO = new ArrayList<>();
        for(BoatReservations c : reservations){
            UserDTO user = new UserDTO(c.getClient());
            ReservationDTO reservationDTO = new ReservationDTO(c.getId(), user, c.getDateStart(), c.getDateEnd(), c.getBoat().getName(), c.getBoat().getId(), c.getStatus().toString());
            reservationsDTO.add(reservationDTO);
        }
        return new ResponseEntity<>(reservationsDTO, HttpStatus.OK);
    }

    @GetMapping("/stats/{boatId}")
    @PreAuthorize("hasAuthority('BOAT_OWNER')")
    public ResponseEntity<StatisticsDTO> boatStats(@PathVariable long boatId){
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        //Da li vikendica postoji
        if(!boatService.existsById(boatId)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li vikendica pripada gazdi koji salje zahtev
        if(boatService.ownerOwnsBoat(userApp.getId(), boatId)){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        List<?> revenueByYears =  appointmentBoatService.getRevanueByYears(boatId);
        List<?> revenueByMonths =  appointmentBoatService.getRevanueForLastYear(boatId);

        List<?> reservationDaysByYears =  appointmentBoatService.getReservationDaysByYears(boatId);
        List<?> reservationDaysForLastYear =  appointmentBoatService.getReservationDaysForLastYear(boatId);

        StatisticsDTO statisticsDTO = new StatisticsDTO(revenueByYears, revenueByMonths, reservationDaysByYears, reservationDaysForLastYear);
        return new ResponseEntity<>(statisticsDTO, HttpStatus.OK);
    }
}
