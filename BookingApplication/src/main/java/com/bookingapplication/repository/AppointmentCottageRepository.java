package com.bookingapplication.repository;

import com.bookingapplication.model.AppointmentCottage;
import com.google.common.collect.ArrayListMultimap;
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

    @Query("select new map(sum(a.pricePerDay) as revenue, extract(year from a.date) as year) " +
            "from AppointmentCottage a " +
            "where a.cottage.id=:cottageId and a.type='RESERVED' and a.date < CURRENT_DATE " +
            "group by extract(year from a.date)")
    public List<?> getRevanueByYears(@Param("cottageId") long cottageId);

    @Query("select new map(sum(a.pricePerDay) as revenue, extract(month from a.date) as month, extract(year from a.date) as year) " +
            "from AppointmentCottage a " +
            "where a.cottage.id=:cottageId and a.type='RESERVED' and a.date < CURRENT_DATE and a.date > (CURRENT_DATE-365) " +
            "group by extract(month from a.date), extract(year from a.date)")
    public List<?> getRevanueForLastYear(@Param("cottageId") long cottageId);

    @Query("select new map(count(a) as numberOfDays, extract(year from a.date) as year) " +
            "from AppointmentCottage a " +
            "where a.cottage.id=:cottageId and a.type='RESERVED' and a.date < CURRENT_DATE " +
            "group by extract(year from a.date)")
    public List<?> getReservationDaysByYears(@Param("cottageId") long cottageId);

    @Query("select new map(count(a) as numberOfDays, extract(month from a.date) as month, extract(year from a.date) as year) " +
            "from AppointmentCottage a " +
            "where a.cottage.id=:cottageId and a.type='RESERVED' and a.date < CURRENT_DATE and a.date > (CURRENT_DATE-365) " +
            "group by extract(month from a.date), extract(year from a.date)")
    public List<?> getReservationDaysForLastYear(@Param("cottageId") long cottageId);
}
