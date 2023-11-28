package com.serdmannwi.practiceprograms.tickettoridewisconsin.service.model;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.AbilityConstants;

import java.util.Objects;

public class Ability {
    private String abilityName;
    private String abilityId;
    private String ownerId;
    private String description;
    private int usesRemaining;
    private int bonusPoints;
    private boolean isActive;
    private boolean isUnlimited;

    //Constructor for initial Map creation
    public Ability(String abilityName, String abilityId, String description, int bonusPoints) {
        this.abilityName = abilityName;
        this.abilityId = abilityId;
        this.description = description;
        this.bonusPoints = bonusPoints;
        this.ownerId = "";
        this.usesRemaining = 3;
        this.isActive = false;
        this.isUnlimited = false;
    }

    //Constructor for when Player chooses Ability
    public Ability(String abilityName, String abilityId, String description, int bonusPoints, String ownerId) {
        this.abilityName = abilityName;
        this.abilityId = abilityId;
        this.description = description;
        this.bonusPoints = bonusPoints;
        this.ownerId = ownerId;
        this.usesRemaining = AbilityConstants.ABILITY_STARTING_USES;
        this.isActive = true;
        this.isUnlimited = false;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(String abilityId) {
        this.abilityId = abilityId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public int getUsesRemaining() {
        return usesRemaining;
    }

    public void setUsesRemaining(int usesRemaining) {
        this.usesRemaining = usesRemaining;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isUnlimited() {
        return isUnlimited;
    }

    public void setUnlimited(boolean unlimited) {
        isUnlimited = unlimited;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abilityName, abilityId, ownerId, description, usesRemaining, bonusPoints, isActive, isUnlimited);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ability other = (Ability) obj;
        return Objects.equals(abilityName, other.abilityName) &&
            Objects.equals(abilityId, other.abilityId) &&
            Objects.equals(ownerId, other.ownerId) &&
            Objects.equals(description, other.description) &&
            usesRemaining == other.usesRemaining &&
            bonusPoints == other.bonusPoints &&
            isActive == other.isActive &&
            isUnlimited == other.isUnlimited;
    }
}
