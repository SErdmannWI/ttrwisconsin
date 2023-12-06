package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.model;

public class FreightContract {
    private String productId;
    //This will be the city of the Product's origin
    private String originCityId;
    //This will be the city of the Player's Freight Station
    private String destinationCityId;
    private int pointsPayout;

    public FreightContract(String productId, String originCityId, int pointsPayout) {
        this.productId = productId;
        this.originCityId = originCityId;
        this.pointsPayout = pointsPayout;
        this.destinationCityId = "";
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public int getPointsPayout() {
        return pointsPayout;
    }

    public void setPointsPayout(int pointsPayout) {
        this.pointsPayout = pointsPayout;
    }
}
