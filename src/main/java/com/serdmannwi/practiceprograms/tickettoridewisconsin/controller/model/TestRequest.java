package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class TestRequest {

    @NotEmpty
    @JsonProperty("testId")
    private String id;

    @NotEmpty
    @JsonProperty("testName")
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
