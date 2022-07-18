package com.bookingapplication.repository;

import com.bookingapplication.model.CottageOwner;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CottageOwnerRepository extends JpaRepository<CottageOwner, Long> {
    public CottageOwner findById(long Id);
}
