package com.sandbox.chat.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Contains information of a buyer
 * @see com.sandbox.chat.models.User
 */

public class Buyer extends User implements Serializable {
    private static final long serialVersionUID = 12332549786754L;
    private LinkedList<Transaction> buyerOrderList;

    /**
     * Constructor for a buyer
     */
    public Buyer(String uid, String email, String firebaseToken, float rating, LinkedList<Transaction> buyerOrderList) {
        super(uid, email, firebaseToken, rating);
        this.buyerOrderList = buyerOrderList;

    }
}

