package com.sandbox.chat.models;

import java.util.ArrayList;

public class DelivererOffer {
    private String delivererOfferID;
    private String cutOffTime;
    private String etaTime;
    private double deliveryFee;
    private ArrayList<String> deliveryLocations;
    private Eatery eatery;
    private Deliverer deliverer;
    private Status status;

    public DelivererOffer(String delivererOfferID, String cutOffTime, String etaTime, double deliveryFee, ArrayList<String> deliveryLocations, Eatery eatery, Deliverer deliverer, Status status) {
        this.delivererOfferID = delivererOfferID;
        this.cutOffTime = cutOffTime;
        this.etaTime = etaTime;
        this.deliveryFee = deliveryFee;
        this.deliveryLocations = deliveryLocations;
        this.eatery = eatery;
        this.deliverer = deliverer;
        this.status = status;
    }

    public String getDelivererOfferID() {
        return delivererOfferID;
    }

    public void setDelivererOfferID(String delivererOfferID) {
        this.delivererOfferID = delivererOfferID;
    }

    public String getCutOffTime() {
        return cutOffTime;
    }

    public void setCutOffTime(String cutOffTime) {
        this.cutOffTime = cutOffTime;
    }

    public String getEtaTime() {
        return etaTime;
    }

    public void setEtaTime(String etaTime) {
        this.etaTime = etaTime;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public ArrayList<String> getDeliveryLocations() {
        return deliveryLocations;
    }

    public void setDeliveryLocations(ArrayList<String> deliveryLocations) {
        this.deliveryLocations = deliveryLocations;
    }

    public Eatery getEatery() {
        return eatery;
    }

    public void setEatery(Eatery eatery) {
        this.eatery = eatery;
    }

    public Deliverer getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(Deliverer deliverer) {
        this.deliverer = deliverer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
