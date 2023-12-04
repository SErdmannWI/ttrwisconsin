package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.effect;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.effect.Effect;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Passive")
public class PassiveEffect extends Effect {
    private String effectInstruction;

    public PassiveEffect() {}

    public PassiveEffect(String name, String id, String eventId, String effectInstruction) {
        super(name, id, eventId);
        this.effectInstruction = effectInstruction;
    }

    public String getEffectInstruction() {
        return effectInstruction;
    }

    public void setEffectInstruction(String effectInstruction) {
        this.effectInstruction = effectInstruction;
    }
}
