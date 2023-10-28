package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Event {
    private String name;
    @Id
    private String eventId;
    private String cardDescription;
    private String effectDescription;
    private String resolutionMessage;
    private String expirationMessage;
    private EventType eventType;
    private boolean isActive;
    private boolean hasExpired;
    private int turnsRemaining;
    private int eventDuration;
}
