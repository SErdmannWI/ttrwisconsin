package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Parent class for all Events
 * All Events will be stored in the same table, organized by "Type"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type", discriminatorType = DiscriminatorType.STRING)
public class Event {
    private String name;
    @Id
    private String eventId;
    private String cardDescription;
    private boolean isActive;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_condition_id", referencedColumnName = "conditionId")
    private EventCondition eventCondition;

    public Event() {}

    public Event(String conditionId, String conditionDescription, String effectId, String name, String eventId,
                 String cardDescription) {
        eventCondition = new EventCondition();
        eventCondition.setConditionId(conditionId);
        eventCondition.setConditionDescription(conditionDescription);
        eventCondition.setEffectId(effectId);
        eventCondition.setConditionMet(false);
        this.name = name;
        this.eventId = eventId;
        this.cardDescription = cardDescription;
        this.isActive = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public boolean isActive() { return isActive; }

    public void setActive(boolean isActive) { this.isActive = isActive; }

    @Entity
    @Table(name = "event_condition")
    public static class EventCondition {
        @Id
        private String conditionId;
        private String conditionDescription;
        private String effectId;
        private List<String> playersEffected;
        @Enumerated(EnumType.STRING)
        private ConditionDetermination conditionDetermination;
        private boolean conditionMet;

        public EventCondition() {}

        public EventCondition(String conditionId, String conditionDescription, String effectId, ConditionDetermination determination) {
            this.conditionId = conditionId;
            this.conditionDescription = conditionDescription;
            this.effectId = effectId;
            playersEffected = new ArrayList<>();
            this.conditionDetermination = determination;
            this.conditionMet = false;
        }

        public String getConditionId() {
            return conditionId;
        }

        public void setConditionId(String conditionId) {
            this.conditionId = conditionId;
        }

        public String getConditionDescription() {
            return conditionDescription;
        }

        public void setConditionDescription(String conditionDescription) {
            this.conditionDescription = conditionDescription;
        }

        public String getEffectId() {
            return effectId;
        }

        public void setEffectId(String effectId) {
            this.effectId = effectId;
        }

        public boolean isConditionMet() {
            return conditionMet;
        }

        public List<String> getPlayersEffected() {
            return playersEffected;
        }

        public void setPlayersEffected(List<String> playersEffected) {
            this.playersEffected = playersEffected;
        }

        public ConditionDetermination getConditionDetermination() {
            return conditionDetermination;
        }

        public void setConditionDetermination(ConditionDetermination conditionDetermination) {
            this.conditionDetermination = conditionDetermination;
        }

        public void setConditionMet(boolean conditionMet) {
            this.conditionMet = conditionMet;
        }
    }
}
