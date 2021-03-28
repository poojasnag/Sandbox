package com.sandbox.chat.models;

import java.util.ArrayList;

public class Buyer extends User {
    private ArrayList<Transaction> buyerOrderList;

    public Buyer(String uid, String email, String firebaseToken, ArrayList<Transaction> buyerOrderList) {
        super(uid, email, firebaseToken);
        this.buyerOrderList = buyerOrderList;
    }
}

