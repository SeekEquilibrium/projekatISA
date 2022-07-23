package com.bookingapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class CottageReport extends Report {
    @ManyToOne(fetch = FetchType.EAGER)
    private Cottage cottage;
    @ManyToOne(fetch = FetchType.EAGER)
    private CottageOwner cottageOwner;
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public CottageReport(CottageOwner cottageOwner, Client client, String description, Boolean reportClient, Cottage cottage) {
        super(description, reportClient);
        this.client = client;
        this.cottageOwner = cottageOwner;
        this.cottage = cottage;
    }
    public CottageOwner getOwner() {
        return cottageOwner;
    }

    public void setOwner(CottageOwner cottageOwner) {
        this.cottageOwner = cottageOwner;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
