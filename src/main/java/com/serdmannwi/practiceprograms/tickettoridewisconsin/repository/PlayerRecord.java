package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.utils.JsonUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Player Record Class
 * Completed Routes, Freight Contracts and Active Effects are serialized to JSON of List<String> containing IDs
 */

@Entity
public class PlayerRecord {
    @Column(columnDefinition = "TEXT")
    private String completedRoutesJson;
    @Column(columnDefinition = "TEXT")
    private String activeFreightContractsJson;
    @Column(columnDefinition = "TEXT")
    private String activeEffectsJson;

    @Id
    private String playerId;
    private String playerName;
    private String iconId;
    private String ownedFreightStationId;
    private String ownedAbilityId;
    private String colorId;
    private int score;
    private int trainsRemaining;
    private int freightContractsCompleted;

    public PlayerRecord() {}

    /**
     * Constructor for new Player
     * @param playerId
     * @param playerName
     * @param iconId
     * @param colorId
     */
    public PlayerRecord(String playerId, String playerName, String iconId, String colorId) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.iconId = iconId;
        this.colorId = colorId;

        completedRoutesJson = JsonUtil.serializeToJson(new ArrayList<>());
        activeFreightContractsJson = JsonUtil.serializeToJson(new ArrayList<>());
        activeEffectsJson = JsonUtil.serializeToJson(new ArrayList<>());

        ownedFreightStationId = "";
        ownedAbilityId = "";

        score = 0;
        trainsRemaining = 60;
        freightContractsCompleted = 0;
    }

    public List<String> getCompletedRoutes() {
        return JsonUtil.deserializeFromJson(completedRoutesJson);
    }

    public List<String> getActiveFreightContracts() {
        return JsonUtil.deserializeFromJson(activeFreightContractsJson);
    }

    public List<String> getActiveEffects() {
        return JsonUtil.deserializeFromJson(activeEffectsJson);
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
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

    public void setCompletedRoutes(List<String> completedRoutes) {
        this.completedRoutesJson = JsonUtil.serializeToJson(completedRoutes);
    }

    public void setActiveFreightContracts(List<String> activeFreightContracts) {
        this.activeFreightContractsJson = JsonUtil.serializeToJson(activeFreightContracts);
    }

    public void setActiveEffects(List<String> activeEffects) {
        this.activeEffectsJson = JsonUtil.serializeToJson(activeEffects);
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
}
