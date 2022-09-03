package com.bookingapplication.repository;

import com.bookingapplication.model.BoatReservations;
import com.bookingapplication.model.CottageReservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BoatReservationsRepository extends JpaRepository<BoatReservations, Long> {
    @Query("select c from BoatReservations c where c.boat.id=:boatId order by c.dateStart desc")
    public ArrayList<BoatReservations> getBoatReservations(@Param("boatId") long boatId);
}
