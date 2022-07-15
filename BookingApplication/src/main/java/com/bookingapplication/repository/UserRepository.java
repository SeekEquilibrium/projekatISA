package com.bookingapplication.repository;

import com.bookingapplication.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
	public UserApp findByEmail(String email);
	public boolean existsByEmail(String email);
	public boolean findById(Integer id);
}
