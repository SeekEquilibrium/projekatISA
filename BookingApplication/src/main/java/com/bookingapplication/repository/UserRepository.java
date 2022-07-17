package com.bookingapplication.repository;

import com.bookingapplication.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
	public UserApp findByUsername(String username);
	public boolean existsByUsername(String username);
	public boolean findById(Integer id);
}
