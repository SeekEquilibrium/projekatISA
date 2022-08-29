package com.bookingapplication.repository;

import com.bookingapplication.model.CottageReservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CottageReservationsRepository extends JpaRepository<CottageReservations, Long> {
}
