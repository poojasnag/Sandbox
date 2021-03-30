package com.sandbox.chat.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains information about a delivery offer
 */
public class DelivererOffer implements Serializable {
    private static final long SerialVersionUID = 4565702394587L;
    /**
     * The ID of the offer
     */
    private String delivererOfferID;
    /**
     * The cut-off time of the offer. Outside this time range, the order will not be available
     */
    private String cutOffTime;
    /**
     * The estimated time of arrival
     */
    private String etaTime;
    /**
     * The delivery fee set by the deliverer
     */
    private double deliveryFee;
    /**
     * The locations that the deliverer is able to deliver to
     */
    private ArrayList<String> deliveryLocations;
    /**
     * The eatery that the deliverer is currently in
     */
    private Eatery eatery;
    /**
     * The information of the deliverer
     */
    private Deliverer deliverer;
//    /**
//     * The status of the order
//     * @deprecated
//     */
//    private Status status;

    /**
     * Constructor for the offer
     * @param delivererOfferID The offer's ID
     * @param cutOffTime The cutoff time
     * @param etaTime The ETA
     * @param deliveryFee Delivery fee
     * @param deliveryLocations List of delivery locations
     * @param eatery The current eatery
     * @param deliverer The deliverer's information

     */
    public DelivererOffer(String delivererOfferID, String cutOffTime, String etaTime, double deliveryFee, ArrayList<String> deliveryLocations, Eatery eatery, Deliverer deliverer) {
        this.delivererOfferID = delivererOfferID;
        this.cutOffTime = cutOffTime;
        this.etaTime = etaTime;
        this.deliveryFee = deliveryFee;
        this.deliveryLocations = deliveryLocations;
        this.eatery = eatery;
        this.deliverer = deliverer;
//        this.status = Status.NO_ORDERS;
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

//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
}
