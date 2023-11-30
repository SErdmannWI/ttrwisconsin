package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class ChooseAbilityRequest {
    @NotEmpty
    @JsonProperty("playerId")
    private String playerId;

    @NotEmpty
    @JsonProperty("abilityId")
    private String abilityId;

    public String getPlayerId() {
        return playerId;
    }

    public String getAbilityId() {
        return abilityId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setAbilityId(String abilityId) {
        this.abilityId = abilityId;
    }
}
