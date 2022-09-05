package com.bookingapplication.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class CottageReport extends Report {
    @ManyToOne(fetch = FetchType.EAGER)
    private Cottage cottage;

    public CottageReport(UserApp owner, Client client, String description, LocalDateTime date, Boolean reportClient , Boolean didNotShowUp, Cottage cottage) {
        super(description, date, reportClient, didNotShowUp, client, owner);
        this.cottage = cottage;
    }

    public CottageReport(Cottage cottage) {
        this.cottage = cottage;
    }

    public CottageReport() {

    }

    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }
}
