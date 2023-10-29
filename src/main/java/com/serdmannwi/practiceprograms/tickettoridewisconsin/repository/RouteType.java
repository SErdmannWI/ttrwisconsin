package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

public enum RouteType {
    FREIGHT_ROUTE("freightRoute"),
    HIGH_SPEED_ROUTE("highSpeedRoute"),
    SPUR_ROUTE("spurRoute");

    private final String value;

    RouteType(String value) { this.value = value; }

    public String getValue() { return value; }
}
