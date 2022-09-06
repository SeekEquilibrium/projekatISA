package com.bookingapplication.repository;

import com.bookingapplication.model.DeleteAccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeleteAccountRequestRepository extends JpaRepository<DeleteAccountRequest, Long> {
}
