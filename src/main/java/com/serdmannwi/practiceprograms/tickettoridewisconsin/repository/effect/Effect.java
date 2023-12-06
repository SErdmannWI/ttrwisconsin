package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.effect;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", discriminatorType = DiscriminatorType.STRING)
@Table(name = "effect")
public class Effect {
    private String name;
    @Id
    private String effectId;
    private String eventId;

    public Effect() {}

    public Effect(String name, String id, String eventId) {
        this.name = name;
        this.effectId = id;
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffectId() {
        return effectId;
    }

    public void setEffectId(String effectId) {
        this.effectId = effectId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
