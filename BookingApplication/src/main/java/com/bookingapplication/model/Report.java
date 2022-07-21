package com.bookingapplication.model;

import javax.persistence.*;

@MappedSuperclass
public class Report {
    @Id
    @SequenceGenerator(name = "reportSeqGen", sequenceName = "reportSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "reportSeqGen")
    private long id;
    @Column
    private UserApp cottageOwner;
    @Column
    private Client client;
    @Column
    private String description;
    @Column
    private Boolean reportClient;

    public Report(UserApp cottageOwner, Client client, String description, Boolean reportClient) {
        this.cottageOwner = cottageOwner;
        this.client = client;
        this.description = description;
        this.reportClient = reportClient;
    }

    public Report() {
    }

    public UserApp getCottageOwner() {
        return cottageOwner;
    }

    public void setCottageOwner(UserApp cottageOwner) {
        this.cottageOwner = cottageOwner;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getReportClient() {
        return reportClient;
    }

    public void setReportClient(Boolean reportClient) {
        this.reportClient = reportClient;
    }
}
