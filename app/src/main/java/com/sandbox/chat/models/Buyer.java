package com.sandbox.chat.models;

import java.util.ArrayList;

/**
 * Contains information of a buyer
 * @see com.sandbox.chat.models.User
 */

public class Buyer extends User {
    private ArrayList<Transaction> buyerOrderList;

    /**
     * Constructor for a buyer
     */
    public Buyer(String uid, String email, String firebaseToken, ArrayList<Transaction> buyerOrderList) {
        super(uid, email, firebaseToken);
        this.buyerOrderList = buyerOrderList;
    }
}

