package com.bookingapplication.dto;

public class AdminDeleteResponseDTO {
    private long requestId;
    private boolean acceptDeletion;

    public AdminDeleteResponseDTO() {
    }

    public AdminDeleteResponseDTO(long requestId, boolean acceptDeletion) {
        this.requestId = requestId;
        this.acceptDeletion = acceptDeletion;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public boolean isAcceptDeletion() {
        return acceptDeletion;
    }

    public void setAcceptDeletion(boolean acceptDeletion) {
        this.acceptDeletion = acceptDeletion;
    }
}
