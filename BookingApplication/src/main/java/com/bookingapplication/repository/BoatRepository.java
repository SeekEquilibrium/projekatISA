package com.bookingapplication.repository;

import com.bookingapplication.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {
    public Boolean existsByName(String name);
    public Boat findByName(String name);
    public Boat findById(long id);
}
