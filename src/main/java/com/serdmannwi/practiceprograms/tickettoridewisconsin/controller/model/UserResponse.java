package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class UserResponse {

    @NotEmpty
    @JsonProperty("userId")
    private String id;

    @NotEmpty
    @JsonProperty("userName")
    private String userName;

    @NotEmpty
    @JsonProperty("userNumber")
    private int userNumber;
}
