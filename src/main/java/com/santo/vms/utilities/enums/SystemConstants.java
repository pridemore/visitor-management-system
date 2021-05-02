package com.santo.vms.utilities.enums;

public enum SystemConstants {
    UPDATED("Update Successful"),
    NOT_FOUND("The entity was not found"),
    DELETED("");

    private final String message;

    SystemConstants(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }


}
