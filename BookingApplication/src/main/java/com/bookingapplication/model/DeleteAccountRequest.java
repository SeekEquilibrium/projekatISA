package com.bookingapplication.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class DeleteAccountRequest {
    @Id
    @SequenceGenerator(name = "deleteSeqGen", sequenceName = "deleteSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "deleteSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserApp userApp;
    @Column(columnDefinition="TEXT")
    private String reason;
    @Column
    @Enumerated(EnumType.STRING)
    private DeleteAccountRequestStatus status;

    public DeleteAccountRequest(UserApp userApp, String reason) {
        this.userApp = userApp;
        this.reason = reason;
        this.status = DeleteAccountRequestStatus.PENDING;
    }

    public DeleteAccountRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public DeleteAccountRequestStatus getStatus() {
        return status;
    }

    public void setStatus(DeleteAccountRequestStatus status) {
        this.status = status;
    }
}
