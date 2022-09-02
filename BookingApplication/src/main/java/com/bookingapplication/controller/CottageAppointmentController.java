package com.bookingapplication.controller;

import com.bookingapplication.dto.*;
import com.bookingapplication.dto.RevanueAndVisists.StatisticsDTO;
import com.bookingapplication.dto.reservationInfo.ReservationDTO;
import com.bookingapplication.model.*;
import com.bookingapplication.service.*;
import com.bookingapplication.validation.DateValidation;
import com.google.common.collect.ArrayListMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointment/cottage")
public class CottageAppointmentController {
    @Autowired
    private CottageService cottageService;
    @Autowired
    private UserService userService;
    @Autowired
    private CottageOwnerService cottageOwnerService;
    @Autowired
    private DateValidation dateValidation;
    @Autowired
    private AppointmentCottageService appointmentCottageService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CottageReservationsService cottageReservationsService;

    @PostMapping("/defineAvailability")
    @PreAuthorize("hasAuthority('COTTAGE_OWNER')")
    public ResponseEntity<DefineCottageAvailabilityResponseDTO> defineAvailabilityOrActions(@Valid @RequestBody DefineCottageAvailabilityRequestDTO request){
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        CottageOwner cottageOwner = cottageOwnerService.findById(userApp.getId());
        //Da li vikendica postoji
        if(!cottageService.existsById(request.getCottageId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li vikendica pripada gazdi koji salje zahtev
        if(cottageService.ownerOwnsCottage(cottageOwner.getId(), request.getCottageId())){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        //Da li je  datum u buducnosti, da li je pocetak pre kraja
        if(dateValidation.isDateBeforeToday(request.getStartDate()) ||
                !dateValidation.isFirstBeforeSecondDate(request.getStartDate(), request.getEndDate())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        DefineCottageAvailabilityResponseDTO response = null;
        //Da li je definisanje akcija ili slobodnih termina
        if(request.isHasAction()){
            response = appointmentCottageService.DefineCottageAction(request);
        }else{
            response = appointmentCottageService.DefineCottageAvailability(request);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getCottageAvailabilityAndReservations/{cottageId}")
    @PreAuthorize("hasAuthority('COTTAGE_OWNER')")
    public ResponseEntity<ArrayList<CottageAvailabilityDTO>> getCottageAvailabilityAndReservations(@PathVariable long cottageId){
        if(!cottageService.existsById(cottageId)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        ArrayList<AppointmentCottage> list = appointmentCottageService.GetCottageAvailabilityAndReservations(cottageId);
        ArrayList<CottageAvailabilityDTO> listDTO = new ArrayList<>();
        for(AppointmentCottage a : list){
            CottageAvailabilityDTO cottageAvailabilityDTO = new CottageAvailabilityDTO(a);
            listDTO.add(cottageAvailabilityDTO);
        }
        return new ResponseEntity<ArrayList<CottageAvailabilityDTO>>(listDTO, HttpStatus.OK);
    }

    @GetMapping("/getCottageAvailability/{cottageId}")
    public ResponseEntity<ArrayList<CottageAvailabilityDTO>> getCottageAvailability(@PathVariable long cottageId){
        if(!cottageService.existsById(cottageId)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        ArrayList<AppointmentCottage> list = appointmentCottageService.GetCottageAvailability(cottageId);
        ArrayList<CottageAvailabilityDTO> listDTO = new ArrayList<>();
        for(AppointmentCottage a : list){
            CottageAvailabilityDTO cottageAvailabilityDTO = new CottageAvailabilityDTO(a);
            listDTO.add(cottageAvailabilityDTO);
        }
        return new ResponseEntity<ArrayList<CottageAvailabilityDTO>>(listDTO, HttpStatus.OK);
    }

    @GetMapping("/getCottageActions/{cottageId}")
    @PreAuthorize("hasAuthority('COTTAGE_OWNER')")
    public ResponseEntity<ArrayList<CottageAvailabilityDTO>> getCottageActions(@PathVariable long cottageId){
        if(!cottageService.existsById(cottageId)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        ArrayList<AppointmentCottage> list = appointmentCottageService.GetCottageActions(cottageId);
        ArrayList<CottageAvailabilityDTO> listDTO = new ArrayList<>();
        for(AppointmentCottage a : list){
            CottageAvailabilityDTO cottageAvailabilityDTO = new CottageAvailabilityDTO(a);
            listDTO.add(cottageAvailabilityDTO);
        }
        return new ResponseEntity<ArrayList<CottageAvailabilityDTO>>(listDTO, HttpStatus.OK);
    }

    @PostMapping("/ownerCreateReservation")
    @PreAuthorize("hasAuthority('COTTAGE_OWNER')")
    public ResponseEntity<CottageReservationResponseDTO> cottageOwnerReservationRequest (@Valid @RequestBody CottageOwnerReservationRequestDTO request) {
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        CottageOwner cottageOwner = cottageOwnerService.findById(userApp.getId());
        //Da li vikendica postoji
        if(!cottageService.existsById(request.getCottageId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li vikendica pripada gazdi koji salje zahtev
        if(cottageService.ownerOwnsCottage(request.getClientId(), request.getCottageId())){
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
        CottageReservationResponseDTO response = appointmentCottageService.CreateReservationForClient(
                request.getClientId(), request.getCottageId(),request.getStartDate(),request.getEndDate());
        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/clientCreateReservation")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<CottageReservationResponseDTO> clientReservationRequest (@Valid @RequestBody CottageClientReservationRequestDTO request) {
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        //Da li vikendica postoji
        if(!cottageService.existsById(request.getCottageId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li je  datum u buducnosti, da li je pocetak pre kraja
        if(dateValidation.isDateBeforeToday(request.getStartDate()) ||
                !dateValidation.isFirstBeforeSecondDate(request.getStartDate(), request.getEndDate())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        CottageReservationResponseDTO response = appointmentCottageService.CreateReservationForClient(
                userApp.getId(),request.getCottageId(),request.getStartDate(),request.getEndDate()
        );
        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/clientBookAction")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<CottageReservationResponseDTO> clientBookAction (@Valid @RequestBody CottageClientReservationRequestDTO request){
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        //Da li vikendica postoji
        if(!cottageService.existsById(request.getCottageId())){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li je  datum u buducnosti, da li je pocetak pre kraja
        if(dateValidation.isDateBeforeToday(request.getStartDate()) ||
                !dateValidation.isFirstBeforeSecondDate(request.getStartDate(), request.getEndDate())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        //Da li je akcija u pitanju
        if(!appointmentCottageService.CheckActionAvailability(request.getCottageId(),request.getStartDate(),request.getEndDate())){
            return  new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }
        CottageReservationResponseDTO response = appointmentCottageService.CreateReservationForClient(
                userApp.getId(),request.getCottageId(),request.getStartDate(),request.getEndDate()
        );
        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cottageReservations/{cottageId}")
    @PreAuthorize("hasAuthority('COTTAGE_OWNER')")
    public ResponseEntity<ArrayList<ReservationDTO>> cottageReservations(@PathVariable long cottageId){
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        //Da li vikendica postoji
        if(!cottageService.existsById(cottageId)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li vikendica pripada gazdi koji salje zahtev
        if(cottageService.ownerOwnsCottage(userApp.getId(), cottageId)){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        ArrayList<CottageReservations> reservations = cottageReservationsService.getCottageReservations(cottageId);
        ArrayList<ReservationDTO> reservationsDTO = new ArrayList<>();
        for(CottageReservations c : reservations){
            UserDTO user = new UserDTO(c.getClient());
            ReservationDTO reservationDTO = new ReservationDTO(c.getId(), user, c.getDateStart(), c.getDateEnd(), c.getCottage().getName(), c.getCottage().getId(), c.getStatus().toString());
            reservationsDTO.add(reservationDTO);
        }
//        ArrayList<AppointmentCottage> appointments = appointmentCottageService.getCottageReservations(cottageId);
//        appointments.sort((o1,o2) -> o1.getDate().compareTo(o2.getDate()));
//        ArrayList<ReservationDTO> reservations = new ArrayList<>();
//        for(AppointmentCottage a : appointments){
//            UserDTO user = new UserDTO(a.getClient());
//            ReservationDTO reservationDTO = new ReservationDTO(a.getId(), user, a.getDate(), a.getPricePerDay(), a.isHasAction());
//            reservations.add(reservationDTO);
//        }

        return new ResponseEntity<>(reservationsDTO, HttpStatus.OK);
    }

    @GetMapping("/stats/{cottageId}")
    @PreAuthorize("hasAuthority('COTTAGE_OWNER')")
    public ResponseEntity<StatisticsDTO> cottageStats(@PathVariable long cottageId){
        UserApp userApp = userService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        //Da li vikendica postoji
        if(!cottageService.existsById(cottageId)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //Da li vikendica pripada gazdi koji salje zahtev
        if(cottageService.ownerOwnsCottage(userApp.getId(), cottageId)){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        List<?> revenueByYears =  appointmentCottageService.getRevanueByYears(cottageId);
        List<?> revenueByMonths =  appointmentCottageService.getRevanueForLastYear(cottageId);

        List<?> reservationDaysByYears =  appointmentCottageService.getReservationDaysByYears(cottageId);
        List<?> reservationDaysForLastYear =  appointmentCottageService.getReservationDaysForLastYear(cottageId);

        StatisticsDTO statisticsDTO = new StatisticsDTO(revenueByYears, revenueByMonths, reservationDaysByYears, reservationDaysForLastYear);
        return new ResponseEntity<>(statisticsDTO, HttpStatus.OK);
    }
}
