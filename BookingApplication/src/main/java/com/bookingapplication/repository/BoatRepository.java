package com.bookingapplication.repository;

import com.bookingapplication.model.Boat;
import com.bookingapplication.model.Cottage;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.ArrayList;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {
    public Boolean existsByName(String name);
    public Boat findByName(String name);
    public Boat findById(long id);
    @Query("select c from Boat c where c.boatOwner.id=:ownerId")
    public ArrayList<Boat> findByBoatOwnerId(@Param("ownerId") long ownerId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Boat c where c.id=:id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Boat findByIdPess(@Param("id") long id);

    @Modifying
    @Query("delete from Boat b where b.id=:id")
    public void deleteById(@Param("id")long id);
}
