package com.bookingapplication.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BoatReportDTO {
    @NotNull
    private long boatId;
    @NotNull
    private long clientId;
    @NotBlank
    private String description;
    @NotNull
    private boolean reportClient;
    @NotNull
    private boolean didNotShowUp;

    public BoatReportDTO(long boatId, long clientId, String description, boolean reportClient, boolean didNotShowUp) {
        this.boatId = boatId;
        this.clientId = clientId;
        this.description = description;
        this.reportClient = reportClient;
        this.didNotShowUp = didNotShowUp;
    }

    public BoatReportDTO() {
    }

    public long getBoatId() {
        return boatId;
    }

    public void setBoatId(long boatId) {
        this.boatId = boatId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isReportClient() {
        return reportClient;
    }

    public void setReportClient(boolean reportClient) {
        this.reportClient = reportClient;
    }

    public boolean isDidNotShowUp() {
        return didNotShowUp;
    }

    public void setDidNotShowUp(boolean didNotShowUp) {
        this.didNotShowUp = didNotShowUp;
    }

}
