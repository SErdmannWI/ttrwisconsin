package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.city;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.utils.JsonUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Each City will be created on startup. Each City's products will be randomly determined based on what region
 * the city is in. For example, a City in region 1 could have either cheese, corn, milk, sausage or beer.
 * Each City will also receive an economy roll value
 */
//TODO- Change products available to only 1?
@Entity
public class City {
    private String name;
    @Id
    private String cityId;
    private int regionId;
    private int economyRoll;
    private String productId;

    public City() {}

    public City(String cityName, String cityId, int regionId) {
        this.name = cityName;
        this.cityId = cityId;
        this.regionId = regionId;
        this.economyRoll = 0;
        this.productId = "";
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

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getEconomyRoll() {
        return economyRoll;
    }

    public void setEconomyRoll(int economyRoll) {
        this.economyRoll = economyRoll;
    }

    public String getProductId() { return productId; }

    public void setProductsAvailable(String productId) {
        this.productId = productId;
    }
}
