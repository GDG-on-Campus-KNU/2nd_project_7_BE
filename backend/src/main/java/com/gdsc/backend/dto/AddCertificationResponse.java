package com.gdsc.backend.dto;

public class AddCertificationResponse {
    private boolean success;
    private String CertificationID;
    private String message;

    public AddCertificationResponse(boolean success, String CertificationID, String message) {
        this.success = success;
        this.CertificationID = CertificationID;
        this.message = message;
    }
}
