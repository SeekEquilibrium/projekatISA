package com.bookingapplication.service;

import com.bookingapplication.model.Role;
import com.bookingapplication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findRole(String name){
        Role role =roleRepository.findByName(name);
        return role;
    }
}
