package com.sandbox.chat.models;

import java.io.Serializable;
import java.util.Objects;

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
    private String buyerName;
    private String delivererName;
    private String transactionID;
    private String eateryName;
    public Transaction (){}
    public Transaction(String eateryName, String transactionID, String buyerName, String delivererName, String buyerID, String delivererOfferID, String delivererID, String buyerLocation, String orderDetails, Status orderStatus, Status delivererStatus, Status buyerStatus) {
        this.eateryName = eateryName;
        this.transactionID = transactionID;
        this.buyerName = buyerName;
        this.delivererName = delivererName;
        this.buyerID = buyerID;
        this.delivererOfferID = delivererOfferID;
        this.delivererID = delivererID;
        this.buyerLocation = buyerLocation;
        this.orderDetails = orderDetails;
        this.orderStatus = orderStatus;
        this.buyerStatus = buyerStatus;
        this.delivererStatus = delivererStatus;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return buyerID.equals(that.buyerID) &&
                delivererOfferID.equals(that.delivererOfferID) &&
                delivererID.equals(that.delivererID) &&

                transactionID.equals(that.transactionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerID, delivererOfferID, delivererID, buyerLocation, orderDetails, buyerStatus, delivererStatus, orderStatus, buyerName, delivererName, transactionID, eateryName);
    }

    public String getEateryName() {
        return eateryName;
    }

    public void setEateryName(String eateryName) {
        this.eateryName = eateryName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getDelivererName() {
        return delivererName;
    }

    public void setDelivererName(String delivererName) {
        this.delivererName = delivererName;
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

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
}



