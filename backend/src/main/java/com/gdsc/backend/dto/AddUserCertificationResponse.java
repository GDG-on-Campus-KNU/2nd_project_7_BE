package com.gdsc.backend.dto;

public class AddUserCertificationResponse {
    private boolean success;
    private String CertificationID;
    private String message;

    public AddUserCertificationResponse(boolean success, String CertificationID, String message) {
        this.success = success;
        this.CertificationID = CertificationID;
        this.message = message;
    }
}
