package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UpdatePlayerRequest {
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
    @JsonProperty("compeltedRoutes")
    private List<String> completedRoutes;

    @NotNull
    @JsonProperty("activeFreightContracts")
    private List<String> activeFreightContracts;

    @NotNull
    @JsonProperty("activeEffects")
    private List<String> activeEffects;

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getIconId() {
        return iconId;
    }

    public String getOwnedFreightStationId() {
        return ownedFreightStationId;
    }

    public String getOwnedAbilityId() {
        return ownedAbilityId;
    }

    public String getColorId() {
        return colorId;
    }

    public int getScore() {
        return score;
    }

    public int getTrainsRemaining() {
        return trainsRemaining;
    }

    public int getFreightContractsCompleted() {
        return freightContractsCompleted;
    }

    public List<String> getCompletedRoutes() {
        return completedRoutes;
    }

    public List<String> getActiveFreightContracts() {
        return activeFreightContracts;
    }

    public List<String> getActiveEffects() {
        return activeEffects;
    }
}
