package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.EventCondition;
import jakarta.validation.constraints.NotNull;

public class SinglePlayerActiveEventResponse {
    @NotNull
    @JsonProperty("eventName")
    private String name;
    @NotNull
    @JsonProperty("eventId")
    private String eventId;
    @NotNull
    @JsonProperty("cardDescription")
    private String cardDescription;
    @NotNull
    @JsonProperty("eventCondition")
    private EventCondition eventCondition;

    public void setName(String name) {
        this.name = name;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public void setEventCondition(EventCondition eventCondition) {
        this.eventCondition = eventCondition;
    }
}
