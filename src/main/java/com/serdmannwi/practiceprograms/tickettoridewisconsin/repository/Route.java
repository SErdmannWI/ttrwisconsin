package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Route {
    private String routeName;
    @Id
    private String routeId;
    private String originCityId;
    private String destinationCityId;
    private String requriedTrainColor;
    private String homeStationId;

    private RouteType routeType;

    private int routeLength;
    private boolean isClaimed;

    public Route() {}

    public Route(String routeName, String routeId, String originCityId, String destinationCityId, String requriedTrainColor,
                 String homeStationId, RouteType routeType, int routeLength, boolean isClaimed) {
        this.routeName = routeName;
        this.routeId = routeId;
        this.originCityId = originCityId;
        this.destinationCityId = destinationCityId;
        this.requriedTrainColor = requriedTrainColor;
        this.homeStationId = homeStationId;
        this.routeType = routeType;
        this.routeLength = routeLength;
        this.isClaimed = isClaimed;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getOriginCityId() {
        return originCityId;
    }

    public void setOriginCityId(String originCityId) {
        this.originCityId = originCityId;
    }

    public String getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(String destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    public String getRequriedTrainColor() {
        return requriedTrainColor;
    }

    public void setRequriedTrainColor(String requriedTrainColor) {
        this.requriedTrainColor = requriedTrainColor;
    }

    public String getHomeStationId() {
        return homeStationId;
    }

    public void setHomeStationId(String homeStationId) {
        this.homeStationId = homeStationId;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public int getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(int routeLength) {
        this.routeLength = routeLength;
    }

    public boolean isClaimed() {
        return isClaimed;
    }

    public void setClaimed(boolean claimed) {
        isClaimed = claimed;
    }
}
