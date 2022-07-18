package com.bookingapplication.repository;

import com.bookingapplication.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CottageRepository extends JpaRepository<Cottage, Long> {
	public Cottage findByNameIgnoringCase(String name);
	public Cottage findById(long id);
	public Boolean existsByName(String name);
}
