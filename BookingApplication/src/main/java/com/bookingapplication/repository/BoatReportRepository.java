package com.bookingapplication.repository;

import com.bookingapplication.model.BoatReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatReportRepository extends JpaRepository<BoatReport, Long> {
    @Query("select count(r) from AppointmentBoat r where r.client.id = :clientId and r.boat.id = :boatId and r.date < CURRENT_DATE")
    public int clientHasReservedBoat(@Param("clientId") long clientId, @Param("boatId") long boatId);
}
