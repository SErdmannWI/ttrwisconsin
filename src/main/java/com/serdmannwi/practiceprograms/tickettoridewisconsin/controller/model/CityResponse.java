package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class CityResponse {
    @NotNull
    @JsonProperty("cityName")
    private String cityName;
    @NotNull
    @JsonProperty("cityId")
    private String cityId;
    @NotNull
    @JsonProperty("productsAvailable")
    private String productsAvailableJson;
    @NotNull
    @JsonProperty("economyRoll")
    private int economyRoll;

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setProductsAvailableJson(String productsAvailableJson) {
        this.productsAvailableJson = productsAvailableJson;
    }

    public void setEconomyRoll(int economyRoll) {
        this.economyRoll = economyRoll;
    }
}
