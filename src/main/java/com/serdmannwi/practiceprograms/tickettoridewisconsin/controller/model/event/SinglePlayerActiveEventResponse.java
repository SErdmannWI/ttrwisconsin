package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }
}
