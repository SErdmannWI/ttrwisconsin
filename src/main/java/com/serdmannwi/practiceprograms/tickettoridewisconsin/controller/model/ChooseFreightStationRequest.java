package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class ChooseFreightStationRequest {
    @NotEmpty
    @JsonProperty("playerId")
    private String playerId;
    @NotEmpty
    @JsonProperty("regionId")
    private int regionId;
    @NotEmpty
    @JsonProperty("freightStationId")
    private String freightStationId;

    public String getPlayerId() {
        return playerId;
    }

    public int getRegionId() {
        return regionId;
    }

    public String getFreightStationId() {
        return freightStationId;
    }
}
