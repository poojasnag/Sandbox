package com.sandbox.chat.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Contains information about a deliverer
 * @see com.sandbox.chat.models.User
 */
public class Deliverer extends User implements Serializable {
    private static final long serialVersionUID = 985675434L;
    private LinkedList<Transaction> delivererOfferList;

    public Deliverer(String uid, String email, String firebaseToken, LinkedList<Transaction> delivererOfferList) {
        super(uid, email, firebaseToken);
        this.delivererOfferList = delivererOfferList;
    }
}
