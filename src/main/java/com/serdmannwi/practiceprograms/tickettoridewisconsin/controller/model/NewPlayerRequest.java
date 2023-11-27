package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

/**
 * New Players will enter their Name, choose a picture for their player icon and choose a color.
 * Other properties will be defined in PlayerService
 */
public class NewPlayerRequest {

    @NotEmpty
    @JsonProperty("playerName")
    private String playerName;

    @NotEmpty
    @JsonProperty("iconId")
    private String playerIconId;

    @NotEmpty
    @JsonProperty("colorId")
    private String playerColorId;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerIconId() {
        return playerIconId;
    }

    public void setPlayerIconId(String playerIconId) {
        this.playerIconId = playerIconId;
    }

    public String getPlayerColorId() {
        return playerColorId;
    }

    public void setPlayerColorId(String playerColorId) {
        this.playerColorId = playerColorId;
    }
}
