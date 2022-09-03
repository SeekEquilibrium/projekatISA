package com.bookingapplication.repository;

import com.bookingapplication.model.Boat;
import com.bookingapplication.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {
    public Boolean existsByName(String name);
    public Boat findByName(String name);
    public Boat findById(long id);
    @Query("select c from Boat c where c.boatOwner.id=:ownerId")
    public ArrayList<Boat> findByBoatOwnerId(@Param("ownerId") long ownerId);
}
