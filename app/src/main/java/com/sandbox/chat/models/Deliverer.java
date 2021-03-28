package com.sandbox.chat.models;

import java.util.ArrayList;

public class Deliverer extends User{
    private ArrayList<DelivererOffer> delivererOfferList;

    public Deliverer(String uid, String email, String firebaseToken, ArrayList<DelivererOffer> delivererOfferList) {
        super(uid, email, firebaseToken);
        this.delivererOfferList = delivererOfferList;
    }
}
