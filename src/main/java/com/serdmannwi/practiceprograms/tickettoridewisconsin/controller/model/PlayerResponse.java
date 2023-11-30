package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

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

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    public String getOwnedFreightStationId() {
        return ownedFreightStationId;
    }

    public void setOwnedFreightStationId(String ownedFreightStationId) {
        this.ownedFreightStationId = ownedFreightStationId;
    }

    public String getOwnedAbilityId() {
        return ownedAbilityId;
    }

    public void setOwnedAbilityId(String ownedAbilityId) {
        this.ownedAbilityId = ownedAbilityId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTrainsRemaining() {
        return trainsRemaining;
    }

    public void setTrainsRemaining(int trainsRemaining) {
        this.trainsRemaining = trainsRemaining;
    }

    public int getFreightContractsCompleted() {
        return freightContractsCompleted;
    }

    public void setFreightContractsCompleted(int freightContractsCompleted) {
        this.freightContractsCompleted = freightContractsCompleted;
    }

    public List<String> getCompletedRoutes() {
        return completedRoutes;
    }

    public void setCompletedRoutes(List<String> completedRoutes) {
        this.completedRoutes = completedRoutes;
    }

    public List<String> getActiveFreightContracts() {
        return activeFreightContracts;
    }

    public void setActiveFreightContracts(List<String> activeFreightContracts) {
        this.activeFreightContracts = activeFreightContracts;
    }

    public List<String> getActiveEffects() {
        return activeEffects;
    }

    public void setActiveEffects(List<String> activeEffects) {
        this.activeEffects = activeEffects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerResponse that = (PlayerResponse) o;
        return score == that.score && trainsRemaining == that.trainsRemaining && freightContractsCompleted == that.freightContractsCompleted && playerName.equals(that.playerName) && playerId.equals(that.playerId) && iconId.equals(that.iconId) && ownedFreightStationId.equals(that.ownedFreightStationId) && ownedAbilityId.equals(that.ownedAbilityId) && colorId.equals(that.colorId) && completedRoutes.equals(that.completedRoutes) && activeFreightContracts.equals(that.activeFreightContracts) && activeEffects.equals(that.activeEffects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, playerId, iconId, ownedFreightStationId, ownedAbilityId, colorId, score, trainsRemaining, freightContractsCompleted, completedRoutes, activeFreightContracts, activeEffects);
    }
}
