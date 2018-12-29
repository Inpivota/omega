package com.inpivota.omega.dto;

public class ApiResponseDto {

    private boolean success;
    private String message;

    public ApiResponseDto(final boolean success, final String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String isMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
