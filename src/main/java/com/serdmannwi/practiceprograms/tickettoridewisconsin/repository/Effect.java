package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.utils.JsonUtil;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Effect Class
 * Condition IDS, ResourceIDs and Actions are serialized to JSON of List<String> containing IDs
 */

@Entity
public class Effect {
    @Column(columnDefinition = "TEXT")
    private String conditionIdsJson;
    @Column(columnDefinition = "TEXT")
    private String resourcesAffectedJson;
    @Column(columnDefinition = "TEXT")
    private String actionsAffectedJson;

    private String name;
    @Id
    private String id;
    private String eventId;

    private int effectDuration;
    private double scoreMultiplier;
    private int primaryPenaltyPoints;
    private int secondaryPenaltyPoints;
    private boolean canClaimRoute;
    private boolean canUseAbility;
    private boolean canGetFreightContractPoints;

    public Effect() {}

    public Effect(String name, String id, String eventId, int effectDuration, double scoreMultiplier,
                  int primaryPenaltyPoints, int secondaryPenaltyPoints, boolean canClaimRoute,
                  boolean canUseAbility, boolean canGetFreightContractPoints, List<String> conditionIds,
                  List<String> resourceIds, List<String> actionIds) {
        this.name = name;
        this.id = id;
        this.eventId = eventId;
        this.effectDuration = effectDuration;
        this.scoreMultiplier = scoreMultiplier;
        this.primaryPenaltyPoints = primaryPenaltyPoints;
        this.secondaryPenaltyPoints = secondaryPenaltyPoints;
        this.canClaimRoute = canClaimRoute;
        this.canUseAbility = canUseAbility;
        this.canGetFreightContractPoints = canGetFreightContractPoints;
        this.conditionIdsJson = JsonUtil.serializeToJson(conditionIds);
        this.resourcesAffectedJson = JsonUtil.serializeToJson(resourceIds);
        this.actionsAffectedJson = JsonUtil.serializeToJson(actionIds);
    }


    public List<String> getConditionIds() {
        return JsonUtil.deserializeFromJson(conditionIdsJson);
    }

    public List<String> getResourcesAffected() {
        return JsonUtil.deserializeFromJson(resourcesAffectedJson);
    }

    public List<String> getActionsAffected() {
        return JsonUtil.deserializeFromJson(actionsAffectedJson);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEventId() {
        return eventId;
    }

    public int getEffectDuration() {
        return effectDuration;
    }

    public double getScoreMultiplier() {
        return scoreMultiplier;
    }

    public int getPrimaryPenaltyPoints() {
        return primaryPenaltyPoints;
    }

    public int getSecondaryPenaltyPoints() {
        return secondaryPenaltyPoints;
    }

    public boolean getCanClaimRoute() {
        return canClaimRoute;
    }

    public boolean getCanUseAbility() {
        return canUseAbility;
    }

    public boolean getCanGetFreightContractPoints() {
        return canGetFreightContractPoints;
    }
}
