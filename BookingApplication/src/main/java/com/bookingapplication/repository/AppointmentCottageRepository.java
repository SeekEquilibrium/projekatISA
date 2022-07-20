package com.bookingapplication.repository;

import com.bookingapplication.model.AppointmentCottage;
import com.bookingapplication.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentCottageRepository extends JpaRepository<AppointmentCottage, Long> {
}
