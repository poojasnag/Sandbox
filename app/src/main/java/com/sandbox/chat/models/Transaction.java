package com.sandbox.chat.models;

import java.io.Serializable;

/**
 * Contains information about an order between  2 users
 *
 */
public class Transaction implements Serializable {
    private static final long serialVersionUID = 123329876L;
    /**
     * The buyer's user ID
     */
    private String buyerID;
    /**
     * The delivery offer's ID
     */
    private String delivererOfferID;  // change from int to string
    /**
     * The deliverer's user ID
     */
    private String delivererID;
    /**
     * The location to deliver to
     */
    private String buyerLocation;
    /**
     * The content of the order
     */
    private String orderDetails;
    /**
     * The completion status set by the buyer
     */
    private Status buyerStatus;
    /**
     * The completion status set by the deliverer
     */
    private Status delivererStatus;
    /**
     * Overall status of the order
     */
    private Status orderStatus;


    public Transaction(String buyerID, String delivererOfferID, String delivererID, String buyerLocation, String orderDetails, Status orderStatus, Status delivererStatus, Status buyerStatus) {
        this.buyerID = buyerID;
        this.delivererOfferID = delivererOfferID;
        this.delivererID = delivererID;
        this.buyerLocation = buyerLocation;
        this.orderDetails = orderDetails;
        this.orderStatus = orderStatus;
        this.buyerStatus = buyerStatus;
        this.delivererStatus = delivererStatus;

    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setbuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public String getDelivererOfferID() {
        return delivererOfferID;
    }

    public void setDelivererOfferID(String delivererOfferID) {
        this.delivererOfferID = delivererOfferID;
    }

    public String getDelivererID() {
        return delivererID;
    }

    public void setDelivererID(String delivererID) {
        this.delivererID = delivererID;
    }

    public String getBuyerLocation() {
        return buyerLocation;
    }

    public void setBuyerLocation(String buyerLocation) {
        this.buyerLocation = buyerLocation;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Status isBuyerStatus() {
        return buyerStatus;
    }

    public void setBuyerStatus(Status buyerStatus) {
        this.buyerStatus = buyerStatus;
    }

    public Status isDelivererStatus() {
        return delivererStatus;
    }

    public void setDelivererStatus(Status delivererStatus) {
        this.delivererStatus = delivererStatus;
    }

    public Status isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }
}



