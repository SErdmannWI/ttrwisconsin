package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class UserRequest {

    @NotEmpty
    @JsonProperty("userId")
    private String id;

    @NotEmpty
    @JsonProperty("userName")
    private String userName;

    @NotEmpty
    @JsonProperty("userNumber")
    private int userNumber;

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserNumber() {
        return userNumber;
    }
}
