package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.EventType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class EventResponse {
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
    @JsonProperty("effectDescription")
    private String effectDescription;
    @NotNull
    @JsonProperty("resolutionMessage")
    private String resolutionMessage;
    @NotNull
    @JsonProperty("expirationMessage")
    private String expirationMessage;
    @NotNull
    @JsonProperty("eventType")
    private EventType eventType;
    @NotNull
    @JsonProperty("isActive")
    private boolean isActive;
    @NotNull
    @JsonProperty("hasExpired")
    private boolean hasExpired;
    @NotNull
    @JsonProperty("turnsRemaining")
    private int turnsRemaining;
    @NotNull
    @JsonProperty("eventDuration")
    private int eventDuration;

    public void setName(String name) {
        this.name = name;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }

    public void setResolutionMessage(String resolutionMessage) {
        this.resolutionMessage = resolutionMessage;
    }

    public void setExpirationMessage(String expirationMessage) {
        this.expirationMessage = expirationMessage;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setHasExpired(boolean hasExpired) {
        this.hasExpired = hasExpired;
    }

    public void setTurnsRemaining(int turnsRemaining) {
        this.turnsRemaining = turnsRemaining;
    }

    public void setEventDuration(int eventDuration) {
        this.eventDuration = eventDuration;
    }
}
