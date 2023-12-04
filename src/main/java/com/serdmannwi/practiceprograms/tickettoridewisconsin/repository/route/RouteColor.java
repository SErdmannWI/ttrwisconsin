package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.route;

public enum RouteColor {
    BLUE("blue"),
    RED("red"),
    GREEN("green"),
    YELLOW("yellow"),
    ORANGE("orange"),
    BLACK("black"),
    WHITE("white"),
    RAINBOW("rainbow");

    private final String value;

    RouteColor(String value) { this.value = value; }

    public String getValue() { return value; }
}
