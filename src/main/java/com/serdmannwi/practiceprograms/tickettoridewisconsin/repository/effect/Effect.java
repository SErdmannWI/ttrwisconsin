package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.effect;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", discriminatorType = DiscriminatorType.STRING)
public class Effect {
    private String name;
    @Id
    private String id;
    private String eventId;

    public Effect() {}

    public Effect(String name, String id, String eventId) {
        this.name = name;
        this.id = id;
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
