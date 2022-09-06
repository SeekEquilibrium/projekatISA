package com.bookingapplication.dto;

import javax.validation.constraints.NotEmpty;

public class DeleteAccountRequestDTO {
    @NotEmpty
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
