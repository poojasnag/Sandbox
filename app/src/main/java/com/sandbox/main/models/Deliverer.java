package com.sandbox.main.models;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Contains information about a deliverer
 * @see com.sandbox.main.models.User
 * @author Chua Zi Heng
 */
public class Deliverer extends User implements Serializable {
    private static final long serialVersionUID = 985675434L;
    private LinkedList<Transaction> delivererOfferList;

    public Deliverer(String uid, String email, String firebaseToken, int rating,int ratingCount, LinkedList<Transaction> delivererOfferList) {
        super(uid, email, firebaseToken, rating, ratingCount);
        this.delivererOfferList = delivererOfferList;
    }

    public LinkedList<Transaction> getDelivererOfferList() {
        return delivererOfferList;
    }

    public void setDelivererOfferList(LinkedList<Transaction> delivererOfferList) {
        this.delivererOfferList = delivererOfferList;
    }
}
