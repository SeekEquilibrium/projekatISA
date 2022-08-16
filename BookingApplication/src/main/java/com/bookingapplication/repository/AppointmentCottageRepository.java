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
    @Query("select a from AppointmentCottage a where a.cottage.id=:cottageId and a.type='AVAILABLE' and a.date >= CURRENT_DATE")
    public ArrayList<AppointmentCottage> getAvailabeAppointments(@Param("cottageId") long cottageId);
}
