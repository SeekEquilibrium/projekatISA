package com.bookingapplication.service;

import com.bookingapplication.model.Client;
import com.bookingapplication.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client findById(long id) { return clientRepository.findById(id); }
    public Boolean existsById(long id) { return clientRepository.existsById(id); }

}
