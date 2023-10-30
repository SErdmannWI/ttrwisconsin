package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

public enum RouteType {
    FREIGHT_ROUTE("FREIGHT-"),
    HIGH_SPEED_ROUTE("SPEED-"),
    SPUR_ROUTE("SPUR-");

    private final String value;

    RouteType(String value) { this.value = value; }

    public String getValue() { return value; }
}
