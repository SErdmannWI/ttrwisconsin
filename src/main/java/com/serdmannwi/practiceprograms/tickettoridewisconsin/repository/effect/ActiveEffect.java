package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.effect;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.utils.JsonUtil;
import jakarta.persistence.Column;

import java.util.List;

public class ActiveEffect extends Effect {
    @Column(columnDefinition = "TEXT")
    private String conditionIdsJson;
    @Column(columnDefinition = "TEXT")
    private String resourcesAffectedJson;
    @Column(columnDefinition = "TEXT")
    private String actionsAffectedJson;
    private int effectDuration;
    private double scoreMultiplier;
    private int primaryPenaltyPoints;
    private int secondaryPenaltyPoints;
    private boolean canClaimRoute;
    private boolean canUseAbility;
    private boolean canGetFreightContractPoints;

    public ActiveEffect() {}

    public List<String> getConditionIds() {
        return JsonUtil.deserializeFromJson(conditionIdsJson);
    }

    public List<String> getResourcesAffected() {
        return JsonUtil.deserializeFromJson(resourcesAffectedJson);
    }

    public List<String> getActionsAffected() {
        return JsonUtil.deserializeFromJson(actionsAffectedJson);
    }
}
