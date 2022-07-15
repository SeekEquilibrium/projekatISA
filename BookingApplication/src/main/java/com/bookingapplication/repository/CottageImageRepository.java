package com.bookingapplication.repository;

import java.util.ArrayList;

import com.bookingapplication.model.CottageImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CottageImageRepository extends JpaRepository<CottageImage, Long>{
	public ArrayList<CottageImage> findByCottageId(long id);
	
	@Query("select i.path from CottageImage i where i.cottage.id =?1")
	public ArrayList<String> findImagePathsByCottageId(long id);
}
