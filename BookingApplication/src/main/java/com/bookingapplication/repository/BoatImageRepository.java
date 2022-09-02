package com.bookingapplication.repository;

import com.bookingapplication.model.BoatImage;
import com.bookingapplication.model.CottageImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BoatImageRepository extends JpaRepository<BoatImage, Long> {
    public ArrayList<BoatImage> findByBoatId(long id);

    @Query("select i.path from BoatImage i where i.boat.id =?1")
    public ArrayList<String> findImagePathsByBoatId(long id);
}
