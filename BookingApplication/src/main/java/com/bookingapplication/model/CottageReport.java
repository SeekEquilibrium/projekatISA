package com.bookingapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class CottageReport extends Report {
    @ManyToOne(fetch = FetchType.EAGER)
    private Cottage cottage;

    public CottageReport(UserApp cottageOwner, Client client, String description, Boolean reportClient, Cottage cottage) {
        super(cottageOwner, client, description, reportClient);
        this.cottage = cottage;
    }

    public CottageReport(Cottage cottage) {
        this.cottage = cottage;
    }

    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }
}
