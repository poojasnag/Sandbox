package com.sandbox.chat.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains information of a buyer
 * @see com.sandbox.chat.models.User
 */

public class Buyer extends User implements Serializable {
    private static final long serialVersionUID = 12332549786754L;
    private ArrayList<Transaction> buyerOrderList;

    /**
     * Constructor for a buyer
     */
    public Buyer(String uid, String email, ArrayList<Transaction> buyerOrderList) {
        super(uid, email);
        this.buyerOrderList = buyerOrderList;
    }
}

