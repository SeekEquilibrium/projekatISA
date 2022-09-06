package com.bookingapplication.service;

import com.bookingapplication.model.DeleteAccountRequest;
import com.bookingapplication.model.DeleteAccountRequestStatus;
import com.bookingapplication.model.UserApp;
import com.bookingapplication.repository.DeleteAccountRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteAccountRequestService {
    @Autowired
    private DeleteAccountRequestRepository deleteAccountRequestRepository;
    @Autowired
    private UserService userService;

    public boolean deleteAccountRequest(UserApp user, String reason){
        DeleteAccountRequest request = new DeleteAccountRequest(user, reason);
        deleteAccountRequestRepository.save(request);
        return true;
    }

    public boolean adminDeletionResponse(long requestId, boolean acceptedDeletion){
        Optional<DeleteAccountRequest> deleteAccountRequest = deleteAccountRequestRepository.findById(requestId);
        if(acceptedDeletion){
            userService.deleteById(deleteAccountRequest.get().getUserApp().getId());
            deleteAccountRequest.get().setStatus(DeleteAccountRequestStatus.ACCEPTED);
            return true;
        }else{
            deleteAccountRequest.get().setStatus(DeleteAccountRequestStatus.DECLINED);
            return false;
        }
    }
}
