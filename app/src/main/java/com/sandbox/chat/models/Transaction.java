package com.sandbox.chat.models;


public class Transaction {
    private String buyerID;
    private String delivererOfferID;  // change from int to string
    private String delivererID;
    private String buyerLocation;
    private String orderDetails;
    private Status buyerStatus;
    private Status delivererStatus;
    private Status orderStatus;


    public Transaction(String buyerID, String delivererOfferID, String delivererID, String buyerLocation, String orderDetails) {
        this.buyerID = buyerID;
        this.delivererOfferID = delivererOfferID;
        this.delivererID = delivererID;
        this.buyerLocation = buyerLocation;
        this.orderDetails = orderDetails;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
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



