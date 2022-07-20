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
}
