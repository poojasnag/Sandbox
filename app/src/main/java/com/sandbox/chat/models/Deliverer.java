package com.sandbox.chat.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains information about a deliverer
 * @see com.sandbox.chat.models.User
 */
public class Deliverer extends User implements Serializable {
    private static final long serialVersionUID = 985675434L;
    private ArrayList<DelivererOffer> delivererOfferList;

    public Deliverer(String uid, String email, ArrayList<DelivererOffer> delivererOfferList) {
        super(uid, email);
        this.delivererOfferList = delivererOfferList;
    }
}
