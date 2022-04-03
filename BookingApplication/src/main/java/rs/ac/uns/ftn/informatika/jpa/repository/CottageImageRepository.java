package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.jpa.model.CottageImage;

@Repository
public interface CottageImageRepository extends JpaRepository<CottageImage, Long>{
	public ArrayList<CottageImage> findByCottageId(long id);
	
	@Query("select i.path from CottageImage i where i.cottage.id =?1")
	public ArrayList<String> findImagePathsByCottageId(long id);
}
