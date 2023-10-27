package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class TestResponse {

    @NotEmpty
    @JsonProperty("testId")
    private String id;

    @NotEmpty
    @JsonProperty("testName")
    private String name;

    public TestResponse() {}

    public TestResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
