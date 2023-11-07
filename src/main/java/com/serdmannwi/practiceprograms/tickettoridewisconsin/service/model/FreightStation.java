package com.serdmannwi.practiceprograms.tickettoridewisconsin.service.model;

public class FreightStation {
    private String freightStationId;
    private String freightStationName;
    private String ownerId;
    private String cityId;
    private String abilityId;
    private boolean isOwned;

    public FreightStation(String freightStationId, String freightStationName, String cityId) {
        this.freightStationId = freightStationId;
        this.freightStationName = freightStationName;
        this.cityId = cityId;
        this.ownerId = "";
        this.abilityId = "";
        this.isOwned = false;
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
