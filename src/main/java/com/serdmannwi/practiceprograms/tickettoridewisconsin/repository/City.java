package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.utils.JsonUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class City {
    private String name;
    @Id
    private String cityId;
    private int economyRoll;
    @Column(columnDefinition = "TEXT")
    private String productsAvailableJson;

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

    public List<String> getProductsAvailableJson() {
        return JsonUtil.deserializeFromJson(productsAvailableJson);
    }

    public void setProductsAvailableJson(List<String> productsAvailable) {
        this.productsAvailableJson = JsonUtil.serializeToJson(productsAvailable);
    }
}
