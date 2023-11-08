package com.serdmannwi.practiceprograms.tickettoridewisconsin.service.model;

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
}
