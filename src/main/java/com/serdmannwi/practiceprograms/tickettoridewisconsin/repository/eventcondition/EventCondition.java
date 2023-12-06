package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.eventcondition;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.effect.Effect;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event.ConditionDetermination;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event_condition")
public class EventCondition {
    @Id
    private String conditionId;
    private String conditionDescription;
    private String effectId;
    private List<String> playersAffected;
    @Enumerated(EnumType.STRING)
    private ConditionDetermination conditionDetermination;
    private boolean conditionMet;
    @OneToOne
    private Effect effect;

    public EventCondition() {}

    public EventCondition(String conditionId, String conditionDescription, String effectId, ConditionDetermination determination) {
        this.conditionId = conditionId;
        this.conditionDescription = conditionDescription;
        this.effectId = effectId;
        playersAffected = new ArrayList<>();
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

    public List<String> getPlayersAffected() {
        return playersAffected;
    }

    public void setPlayersAffected(List<String> playersAffected) {
        this.playersAffected = playersAffected;
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
