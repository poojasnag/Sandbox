package com.sandbox.chat.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

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
    private ArrayList<String> deliveryLocation;
    /**
     * The eatery that the deliverer is currently in
     */
    private Eatery eatery;
    /**
     * The information of the deliverer
     */
    private Deliverer deliverer;

    private String timestamp;
    private String delivererName;
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
     * @param deliveryLocation Delivery location
     * @param eatery The current eatery
     * @param deliverer The deliverer's information

     */

    public DelivererOffer(String delivererOfferID, String delivererName ,String cutOffTime, String etaTime, double deliveryFee, ArrayList<String> deliveryLocation, Eatery eatery, Deliverer deliverer, String timestamp) {
        this.delivererOfferID = delivererOfferID;
        this.delivererName = delivererName ;
        this.cutOffTime = cutOffTime;
        this.etaTime = etaTime;
        this.deliveryFee = deliveryFee;
        this.deliveryLocation = deliveryLocation;
        this.eatery = eatery;
        this.deliverer = deliverer;
        this.timestamp = timestamp;
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

    public ArrayList<String> getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocations(ArrayList<String>deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDelivererName() {
        return delivererName;
    }

    public void setDelivererName(String delivererName) {
        this.delivererName = delivererName;
    }
}
