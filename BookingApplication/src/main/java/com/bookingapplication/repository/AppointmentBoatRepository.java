package com.bookingapplication.repository;

import com.bookingapplication.model.AppointmentBoat;
import com.bookingapplication.model.AppointmentCottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface AppointmentBoatRepository extends JpaRepository<AppointmentBoat, Long> {
    public Boolean existsByDate(LocalDate date);
    @Query("select a from AppointmentBoat a where a.boat.id=:boatId and a.date=:date")
    public AppointmentBoat findByDate(@Param("date") LocalDate date, @Param("boatId") long boatId);

    //Koristi se za prikaz definisanih termina za vlasnika vikendice
    @Query("select a from AppointmentBoat a where a.boat.id=:boatId and (a.type='AVAILABLE' or a.type='RESERVED')")
    public ArrayList<AppointmentBoat> getAvailabeAppointmentsAndReservations(@Param("boatId") long boatId);

    @Query("select a from AppointmentBoat a where a.boat.id=:boatId and a.hasAction=true")
    public ArrayList<AppointmentBoat> getActionAppointments(@Param("boatId") long boatId);

    @Query("select a from AppointmentBoat a where a.boat.id=:boatId and a.type!='AVAILABLE'")
    public ArrayList<AppointmentBoat> getBoatReservations(@Param("boatId") long boatId);

    @Query("select a from AppointmentBoat a where a.boat.id=:boatId and a.type='AVAILABLE' and a.date >= CURRENT_DATE")
    public ArrayList<AppointmentBoat> getAvailabeAppointments(@Param("boatId") long boatId);

    @Query("select new map(sum(a.pricePerDay) as revenue, extract(year from a.date) as year) " +
            "from AppointmentBoat a " +
            "where a.boat.id=:boatId and a.type='RESERVED' and a.date < CURRENT_DATE " +
            "group by extract(year from a.date)")
    public List<?> getRevanueByYears(@Param("boatId") long boatId);

    @Query("select new map(sum(a.pricePerDay) as revenue, extract(month from a.date) as month, extract(year from a.date) as year) " +
            "from AppointmentBoat a " +
            "where a.boat.id=:boatId and a.type='RESERVED' and a.date < CURRENT_DATE and a.date > (CURRENT_DATE-365) " +
            "group by extract(month from a.date), extract(year from a.date)")
    public List<?> getRevanueForLastYear(@Param("boatId") long boatId);

    @Query("select new map(count(a) as numberOfDays, extract(year from a.date) as year) " +
            "from AppointmentBoat a " +
            "where a.boat.id=:boatId and a.type='RESERVED' and a.date < CURRENT_DATE " +
            "group by extract(year from a.date)")
    public List<?> getReservationDaysByYears(@Param("boatId") long boatId);

    @Query("select new map(count(a) as numberOfDays, extract(month from a.date) as month, extract(year from a.date) as year) " +
            "from AppointmentBoat a " +
            "where a.boat.id=:boatId and a.type='RESERVED' and a.date < CURRENT_DATE and a.date > (CURRENT_DATE-365) " +
            "group by extract(month from a.date), extract(year from a.date)")
    public List<?> getReservationDaysForLastYear(@Param("boatId") long boatId);

    @Query("select count(a) from AppointmentBoat a where a.boat.id=:boatId and a.type='RESERVED' and a.date > CURRENT_DATE")
    public int boatHasFutureReservations(@Param("boatId") long boatId);
}
