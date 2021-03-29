package com.sandbox.chat.mgr;

import android.content.Context;
import android.content.Intent;

import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.Transaction;
import com.sandbox.chat.models.User;
import com.sandbox.chat.ui.activities.EaterySelectionMapActivity;

import java.util.LinkedList;

/**
 * Manager class for BnDActivity
 */
public class BnDMgr {

    public BnDMgr() {
    }
    /**
     * Implements the function to call when the user selects the "Buyer" option
     *
     */


     public Buyer createBuyer(User user)
     {
         //TODO: Call TransactionMgr to get all the transactions
         return new Buyer(user.getUid(), user.getEmail(), user.getFirebaseToken(), user.getRating() , new LinkedList<Transaction>());
     }

    public Deliverer createDeliverer(User user)
    {
        //TODO: Call TransactionMgr to get all the transactions
        return new Deliverer(user.getUid(), user.getEmail(), user.getFirebaseToken(), user.getRating(), new LinkedList<Transaction>());
    }
}
