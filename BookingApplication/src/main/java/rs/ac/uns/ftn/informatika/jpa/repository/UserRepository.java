package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.informatika.jpa.model.UserApp;


@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
	public UserApp findByEmail(String email);
	public boolean existsByEmail(String email);
	public boolean findById(Integer id);
}
