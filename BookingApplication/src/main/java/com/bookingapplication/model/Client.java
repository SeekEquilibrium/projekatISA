package com.bookingapplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client extends UserApp {
    @OneToMany
    (mappedBy = "client", fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    public Set<AppointmentCottage> appointments = new HashSet<AppointmentCottage>();

    @OneToMany
    (mappedBy = "client", fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    public Set<Report> reports = new HashSet<>();

    public Client(String username, String name, String surname, String email, String password, String phoneNumber, Role role) {
        super(username, name, surname, email, password, phoneNumber, role);
    }
    public Client(UserApp userApp) {
        super(userApp.getUsername(), userApp.getName(), userApp.getSurname(), userApp.getEmail(), userApp.getPassword(),
                userApp.getPhoneNumber(), userApp.getRole());
    }

    public Client() {

    }
}
