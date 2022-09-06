package com.bookingapplication.service;

import com.bookingapplication.model.DeleteAccountRequest;
import com.bookingapplication.model.UserApp;
import com.bookingapplication.repository.DeleteAccountRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteAccountRequestService {
    @Autowired
    private DeleteAccountRequestRepository deleteAccountRequestRepository;

    public boolean deleteAccountRequest(UserApp user, String reason){
        DeleteAccountRequest request = new DeleteAccountRequest(user, reason);
        deleteAccountRequestRepository.save(request);
        return true;
    }
}
