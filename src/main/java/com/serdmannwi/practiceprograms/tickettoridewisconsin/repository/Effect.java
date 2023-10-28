//package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;
//
//import jakarta.persistence.ElementCollection;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//
//import java.util.List;
//
//@Entity
//public class Effect {
//    @ElementCollection
//    private List<String> conditionIds;
//    @ElementCollection
//    private List<String> resourcesAffected;
//    @ElementCollection
//    private List<String> actionsAffected;
//
//    private String name;
//    @Id
//    private String id;
//    private String eventId;
//
//    private int effectDuration;
//    private double scoreMultiplier;
//    private int primaryPenaltyPoints;
//    private int secondaryPenaltyPoints;
//    private boolean canClaimRoute;
//    private boolean canUseAbility;
//    private boolean canGetFreightContractPoints;
//
//    public Effect() {}
//
//    public Effect(String name, String id, String eventId, int effectDuration, double scoreMultiplier,
//                  int primaryPenaltyPoints, int secondaryPenaltyPoints, boolean canClaimRoute,
//                  boolean canUseAbility, boolean canGetFreightContractPoints) {
//        this.name = name;
//        this.id = id;
//        this.eventId = eventId;
//        this.effectDuration = effectDuration;
//        this.scoreMultiplier = scoreMultiplier;
//        this.primaryPenaltyPoints = primaryPenaltyPoints;
//        this.secondaryPenaltyPoints = secondaryPenaltyPoints;
//        this.canClaimRoute = canClaimRoute;
//        this.canUseAbility = canUseAbility;
//        this.canGetFreightContractPoints = canGetFreightContractPoints;
//    }
//
//
//    public List<String> getConditionIds() {
//        return conditionIds;
//    }
//
//    public List<String> getResourcesAffected() {
//        return resourcesAffected;
//    }
//
//    public List<String> getActionsAffected() {
//        return actionsAffected;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getEventId() {
//        return eventId;
//    }
//
//    public int getEffectDuration() {
//        return effectDuration;
//    }
//
//    public double getScoreMultiplier() {
//        return scoreMultiplier;
//    }
//
//    public int getPrimaryPenaltyPoints() {
//        return primaryPenaltyPoints;
//    }
//
//    public int getSecondaryPenaltyPoints() {
//        return secondaryPenaltyPoints;
//    }
//
//    public boolean isCanClaimRoute() {
//        return canClaimRoute;
//    }
//
//    public boolean isCanUseAbility() {
//        return canUseAbility;
//    }
//
//    public boolean isCanGetFreightContractPoints() {
//        return canGetFreightContractPoints;
//    }
//}
