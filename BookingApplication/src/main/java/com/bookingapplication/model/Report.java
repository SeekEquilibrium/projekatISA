package com.bookingapplication.model;


import javax.persistence.*;

@MappedSuperclass
public class Report {
    @Id
    @SequenceGenerator(name = "reportSeqGen", sequenceName = "reportSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "reportSeqGen")
    private long id;
    @Column
    private String description;
    @Column
    private Boolean reportClient;

    public Report(String description, Boolean reportClient) {
        this.description = description;
        this.reportClient = reportClient;
    }

    public Report() {
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
