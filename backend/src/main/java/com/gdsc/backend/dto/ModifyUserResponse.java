package com.gdsc.backend.dto;

public class ModifyUserResponse {
    private boolean success;
    private String message;

    public ModifyUserResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
