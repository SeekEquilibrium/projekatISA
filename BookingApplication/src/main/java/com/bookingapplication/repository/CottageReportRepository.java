package com.bookingapplication.repository;

import com.bookingapplication.model.AppointmentCottage;
import com.bookingapplication.model.CottageReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CottageReportRepository extends JpaRepository<CottageReport, Long> {
    @Query("select count(r) from AppointmentCottage r where r.client.id = :clientId and r.cottage.id = :cottageId and r.date < CURRENT_DATE")
    public int clientHasReservedCottage(@Param("clientId") long clientId, @Param("cottageId") long cottageId);
}
