package com.bookingapplication.repository;

import com.bookingapplication.model.AppointmentBoat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AppointmentBoatRepository extends JpaRepository<AppointmentBoat, Long> {
    public Boolean existsByDate(LocalDate date);
    public AppointmentBoat findByDate(LocalDate date);
}
