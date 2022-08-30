package com.bookingapplication.repository;

import com.bookingapplication.model.AppointmentCottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface AppointmentCottageRepository extends JpaRepository<AppointmentCottage, Long> {
    public Boolean existsByDate(LocalDate date);
    public AppointmentCottage findByDate(LocalDate date);

    //Koristi se za prikaz definisanih termina za vlasnika vikendice
    @Query("select a from AppointmentCottage a where a.cottage.id=:cottageId and (a.type='AVAILABLE' or a.type='RESERVED')")
    public ArrayList<AppointmentCottage> getAvailabeAppointmentsAndReservations(@Param("cottageId") long cottageId);

    @Query("select a from AppointmentCottage a where a.cottage.id=:cottageId and a.hasAction=true")
    public ArrayList<AppointmentCottage> getActionAppointments(@Param("cottageId") long cottageId);

    @Query("select a from AppointmentCottage a where a.cottage.id=:cottageId and a.type!='AVAILABLE'")
    public ArrayList<AppointmentCottage> getCottageReservations(@Param("cottageId") long cottageId);

    @Query("select a from AppointmentCottage a where a.cottage.id=:cottageId and a.type='AVAILABLE' and a.date >= CURRENT_DATE")
    public ArrayList<AppointmentCottage> getAvailabeAppointments(@Param("cottageId") long cottageId);
}
