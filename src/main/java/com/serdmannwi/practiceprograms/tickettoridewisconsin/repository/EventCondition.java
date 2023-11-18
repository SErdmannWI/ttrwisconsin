package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import jakarta.persistence.*;

@Entity
public class EventCondition {
    @Id
    private String conditionId;
    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;
    private String conditionDescription;
    private boolean conditionMet;

    public EventCondition() {}

    public EventCondition (String conditionId, Event event, String conditionDescription) {
        this.conditionId = conditionId;
        this.event = event;
        this.conditionDescription = conditionDescription;
        this.conditionMet = false;
    }
}
