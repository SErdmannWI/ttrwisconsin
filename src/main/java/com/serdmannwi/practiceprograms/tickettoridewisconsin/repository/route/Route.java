package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.route;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Route {
    @Id
    private String routeId;
    private String originCityId;
    private String originCityName;
    private String destinationCityId;
    private String destinationCityName;
    private RouteColor requriedTrainColor;
    private RouteType routeType;
    private int routeLength;
    private boolean isClaimed;

    public Route() {}

    //Constructor for Freight Route
    public Route(String originCityId, String originCityName, String destinationCityId,
                 String destinationCityName, RouteColor requriedTrainColor, RouteType routeType, int routeLength) {
        this.routeId = routeType.getValue() + originCityId + destinationCityId;
        this.originCityId = originCityId;
        this.originCityName = originCityName;
        this.destinationCityId = destinationCityId;
        this.destinationCityName = destinationCityName;
        this.requriedTrainColor = requriedTrainColor;
        this.routeType = routeType;
        this.routeLength = routeLength;
        this.isClaimed = false;
    }

    //Constructor for High Speed and Spur Route
    public Route(String routeId, String originCityId, String originCityName, String destinationCityId,
                 String destinationCityName, RouteColor requriedTrainColor, RouteType routeType, int routeLength) {
        this.routeId = routeId;
        this.originCityId = originCityId;
        this.originCityName = originCityName;
        this.destinationCityId = destinationCityId;
        this.destinationCityName = destinationCityName;
        this.requriedTrainColor = requriedTrainColor;
        this.routeType = routeType;
        this.routeLength = routeLength;
        this.isClaimed = false;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getOriginCityId() {
        return originCityId;
    }

    public String getOriginCityName() {
        return originCityName;
    }

    public String getDestinationCityId() {
        return destinationCityId;
    }

    public String getDestinationCityName() {
        return destinationCityName;
    }

    public RouteColor getRequriedTrainColor() {
        return requriedTrainColor;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public int getRouteLength() {
        return routeLength;
    }

    public boolean isClaimed() {
        return isClaimed;
    }

    public void setClaimed() {
        this.isClaimed = true;
    }

    @Override
    public String toString() {
        return originCityName + " to " + destinationCityName;
    }
}
