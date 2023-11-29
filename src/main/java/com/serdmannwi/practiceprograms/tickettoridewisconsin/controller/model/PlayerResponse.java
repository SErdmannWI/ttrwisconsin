package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PlayerResponse {
    @NotEmpty
    @JsonProperty("playerName")
    private String playerName;

    @NotEmpty
    @JsonProperty("playerId")
    private String playerId;

    @NotEmpty
    @JsonProperty("iconId")
    private String iconId;

    @NotEmpty
    @JsonProperty("ownedFreightStation")
    private String ownedFreightStationId;

    @NotEmpty
    @JsonProperty("ownedAbility")
    private String ownedAbilityId;

    @NotEmpty
    @JsonProperty("color")
    private String colorId;

    @NotEmpty
    @JsonProperty("score")
    private int score;

    @NotEmpty
    @JsonProperty("trainsRemaining")
    private int trainsRemaining;

    @NotEmpty
    @JsonProperty("freightContractsCompleted")
    private int freightContractsCompleted;

    @NotNull
    @JsonProperty("completedRoutes")
    private List<String> completedRoutes;

    @NotNull
    @JsonProperty("activeFreightContracts")
    private List<String> activeFreightContracts;

    @NotNull
    @JsonProperty("activeEffects")
    private List<String> activeEffects;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    public void setOwnedFreightStationId(String ownedFreightStationId) {
        this.ownedFreightStationId = ownedFreightStationId;
    }

    public void setOwnedAbilityId(String ownedAbilityId) {
        this.ownedAbilityId = ownedAbilityId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTrainsRemaining(int trainsRemaining) {
        this.trainsRemaining = trainsRemaining;
    }

    public void setFreightContractsCompleted(int freightContractsCompleted) {
        this.freightContractsCompleted = freightContractsCompleted;
    }

    public void setCompletedRoutes(List<String> completedRoutes) {
        this.completedRoutes = completedRoutes;
    }

    public void setActiveFreightContracts(List<String> activeFreightContracts) {
        this.activeFreightContracts = activeFreightContracts;
    }

    public void setActiveEffects(List<String> activeEffects) {
        this.activeEffects = activeEffects;
    }
}
