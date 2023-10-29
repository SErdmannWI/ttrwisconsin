package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.utils.JsonUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class City {
    private String name;
    @Id
    private String cityId;
    private int economyRoll;
    @Column(columnDefinition = "TEXT")
    private String productsAvailableJson;

    public City() {}

    public City(String cityName, String cityId) {
        this.name = cityName;
        this.cityId = cityId;
        this.economyRoll = 0;
        this.productsAvailableJson = JsonUtil.serializeToJson(new ArrayList<>());
    }

    public City(String cityName, String cityId, List<String> cityProdcuts, int economyRoll) {
        this.name = cityName;
        this.cityId = cityId;
        this.productsAvailableJson = JsonUtil.serializeToJson(cityProdcuts);
        this.economyRoll = economyRoll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public int getEconomyRoll() {
        return economyRoll;
    }

    public void setEconomyRoll(int economyRoll) {
        this.economyRoll = economyRoll;
    }

    public List<String> getProductsAvailable() {
        return JsonUtil.deserializeFromJson(productsAvailableJson);
    }

    public String getProductsAvailableJson() { return productsAvailableJson; }

    public void setProductsAvailable(List<String> productsAvailable) {
        this.productsAvailableJson = JsonUtil.serializeToJson(productsAvailable);
    }
}
