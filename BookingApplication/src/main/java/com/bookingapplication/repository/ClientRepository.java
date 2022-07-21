package com.bookingapplication.repository;

import com.bookingapplication.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    public Client findById(long Id);
    public Boolean existsById(long id);
}
