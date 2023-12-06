package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.model;

public class FreightStation {
    private String freightStationId;
    private String freightStationName;
    private String ownerId;
    private String cityId;
    private int regionId;
    private String abilityId;
    private boolean isOwned;

    //Constructor for initial Freight Station creation on startup
    public FreightStation(String freightStationId, String freightStationName, String cityId, int regionId) {
        this.freightStationId = freightStationId;
        this.freightStationName = freightStationName;
        this.cityId = cityId;
        this.regionId = regionId;
        this.ownerId = "";
        this.abilityId = "";
        this.isOwned = false;
    }

    //Constructor for when Player chooses a Freight Station
    public FreightStation(String freightStationId, String freightStationName, String ownerId, String cityId, int regionId) {
        this.freightStationId = freightStationId;
        this.freightStationName = freightStationName;
        this.ownerId = ownerId;
        this.cityId = cityId;
        this. regionId = regionId;
        this.abilityId = "";
        this.isOwned = true;
    }

    public String getFreightStationId() {
        return freightStationId;
    }

    public String getFreightStationName() {
        return freightStationName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getCityId() {
        return cityId;
    }

    public String getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(String abilityId) {
        this.abilityId = abilityId;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean owned) {
        isOwned = owned;
    }
}
