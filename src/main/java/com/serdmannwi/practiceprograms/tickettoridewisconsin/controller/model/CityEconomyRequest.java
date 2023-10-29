package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class CityEconomyRequest {
    @NotNull
    @JsonProperty("cityId")
    private String cityId;
    @NotNull
    @JsonProperty("productsAvailable")
    private String productsAvailableJson;
    @NotNull
    @JsonProperty("economyRoll")
    private int economyRoll;

    public String getCityId() {
        return cityId;
    }

    public String getProductsAvailableJson() {
        return productsAvailableJson;
    }

    public int getEconomyRoll() {
        return economyRoll;
    }
}
