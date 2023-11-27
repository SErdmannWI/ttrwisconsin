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

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    public boolean isConditionMet() {
        return conditionMet;
    }

    public void setConditionMet(boolean conditionMet) {
        this.conditionMet = conditionMet;
    }
}
