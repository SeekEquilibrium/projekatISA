package com.bookingapplication.repository;

import com.bookingapplication.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface CottageRepository extends JpaRepository<Cottage, Long> {
	public Cottage findByNameIgnoringCase(String name);
	public Cottage findById(long id);
	public Boolean existsByName(String name);
	@Query("select c from Cottage c where c.cottageOwner.id=:ownerId")
	public ArrayList<Cottage> findByCottageOwnerId(@Param("ownerId") long ownerId);
}
