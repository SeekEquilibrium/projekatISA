package com.bookingapplication.repository;

import com.bookingapplication.model.AppointmentCottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AppointmentCottageRepository extends JpaRepository<AppointmentCottage, Long> {
    public Boolean existsByDate(LocalDate date);
    public AppointmentCottage findByDate(LocalDate date);
}
