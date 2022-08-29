package com.bookingapplication.repository;

import com.bookingapplication.model.CottageReservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CottageReservationsRepository extends JpaRepository<CottageReservations, Long> {

    @Query("select c from CottageReservations c where c.cottage.id=:cottageId")
    public ArrayList<CottageReservations> getCottageReservations(@Param("cottageId") long cottageId);
}
