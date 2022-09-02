package com.bookingapplication.repository;

import com.bookingapplication.model.BoatOwner;
import com.bookingapplication.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatOwnerRepository extends JpaRepository<BoatOwner, Long> {
    public BoatOwner findById(long id);
}
