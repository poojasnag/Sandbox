package com.sandbox.chat.models;

public class Transaction {
    private String buyerID;
    private int delivererOfferID;
    private String delivererID;
    private String buyerLocation;
    private String orderDetails;
    private String getBuyerID;
    private int getDelivererOfferID ;
    private String getDelivererID;
    private String getBuyerLocation;
    private String getOrderDetails;


    public Transaction(String buyerID, int delivererOfferID, String delivererID, String buyerLocation, String orderDetails, String getBuyerID, int getDelivererOfferID, String getDelivererID, String getBuyerLocation, String getOrderDetails) {
        this.buyerID = buyerID;
        this.delivererOfferID = delivererOfferID;
        this.delivererID = delivererID;
        this.buyerLocation = buyerLocation;
        this.orderDetails = orderDetails;
        this.getBuyerID = getBuyerID;
        this.getDelivererOfferID = getDelivererOfferID;
        this.getDelivererID = getDelivererID;
        this.getBuyerLocation = getBuyerLocation;
        this.getOrderDetails = getOrderDetails;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public int getDelivererOfferID() {
        return delivererOfferID;
    }

    public void setDelivererOfferID(int delivererOfferID) {
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

    public String getGetBuyerID() {
        return getBuyerID;
    }

    public void setGetBuyerID(String getBuyerID) {
        this.getBuyerID = getBuyerID;
    }

    public int getGetDelivererOfferID() {
        return getDelivererOfferID;
    }

    public void setGetDelivererOfferID(int getDelivererOfferID) {
        this.getDelivererOfferID = getDelivererOfferID;
    }

    public String getGetDelivererID() {
        return getDelivererID;
    }

    public void setGetDelivererID(String getDelivererID) {
        this.getDelivererID = getDelivererID;
    }

    public String getGetBuyerLocation() {
        return getBuyerLocation;
    }

    public void setGetBuyerLocation(String getBuyerLocation) {
        this.getBuyerLocation = getBuyerLocation;
    }

    public String getGetOrderDetails() {
        return getOrderDetails;
    }

    public void setGetOrderDetails(String getOrderDetails) {
        this.getOrderDetails = getOrderDetails;
    }
}



