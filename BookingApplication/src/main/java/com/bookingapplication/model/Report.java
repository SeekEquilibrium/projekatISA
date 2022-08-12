package com.bookingapplication.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Report {
    @Id
    @SequenceGenerator(name = "reportSeqGen", sequenceName = "reportSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "reportSeqGen")
    private long id;
    @Column
    private String description;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;
    @Column
    private Boolean reportClient;
    @Column
    private Boolean didNotShowUp;
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserApp owner;

    public Report(String description, LocalDateTime date, Boolean reportClient, Boolean didNotShowUp, Client client, UserApp owner) {
        this.description = description;
        this.date = date;
        this.reportClient = reportClient;
        this.didNotShowUp = didNotShowUp;
        this.client = client;
        this.owner = owner;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public UserApp getOwner() {
        return owner;
    }

    public void setOwner(UserApp owner) {
        this.owner = owner;
    }
    public Boolean getDidNotShowUp() {
        return didNotShowUp;
    }

    public void setDidNotShowUp(Boolean didNotShowUp) {
        this.didNotShowUp = didNotShowUp;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
