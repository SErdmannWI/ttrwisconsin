package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.effect;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * ActiveConditionalDurationEffect is an Effect which will affect the Player in the given Event if the condition is not
 * satisfied. The Effect will be in place as long as the roundsAffected is not 0. The Effect will prevent the Player
 * from using the action affected by this, as determined by the Action ID (actionAffected)
 */
@Entity
@DiscriminatorValue("ActiveConditionalDuration")
public class ActiveConditionalDurationEffect extends Effect {
   private String effectInstruction;
   private String actionAffected;
   private int roundsAffected;
   private boolean isActive;


   public ActiveConditionalDurationEffect() {}

   public ActiveConditionalDurationEffect(String name, String id, String eventId, String effectInstruction, String actionAffected,
                                          int roundsAffected) {
       super(name, id, eventId);
       this.effectInstruction = effectInstruction;
       this.actionAffected = actionAffected;
       this.roundsAffected = roundsAffected;
       this.isActive = true;
   }

    public String getEffectInstruction() {
        return effectInstruction;
    }

    public void setEffectInstruction(String effectInstruction) {
        this.effectInstruction = effectInstruction;
    }

    public String getActionAffected() {
        return actionAffected;
    }

    public void setActionAffected(String actionAffected) {
        this.actionAffected = actionAffected;
    }

    public int getRoundsAffected() {
        return roundsAffected;
    }

    public void setRoundsAffected(int roundsAffected) {
        this.roundsAffected = roundsAffected;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
