package com.bookingapplication.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CottageReportDTO {
    @NotNull
    private long cottageId;
    @NotNull
    private long clientId;
    @NotBlank
    private String description;
    @NotNull
    private boolean reportClient;
    @NotNull
    private boolean didNotShowUp;

    public CottageReportDTO(long cottageId, long clientId, String description, boolean reportClient, boolean didNotShowUp) {
        this.cottageId = cottageId;
        this.clientId = clientId;
        this.description = description;
        this.reportClient = reportClient;
        this.didNotShowUp = didNotShowUp;
    }

    public CottageReportDTO() {
    }

    public long getCottageId() {
        return cottageId;
    }

    public void setCottageId(long cottageId) {
        this.cottageId = cottageId;
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
