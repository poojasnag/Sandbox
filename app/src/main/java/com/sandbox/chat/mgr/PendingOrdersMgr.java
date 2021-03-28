package com.sandbox.chat.mgr;

import com.sandbox.chat.models.Transaction;

import java.util.LinkedList;

/**
 * Manager class for PendingOrdersActivity
 */
public class PendingOrdersMgr {
    public PendingOrdersMgr() {
    }

    /**
     * Retrieve the list of incomplete orders
     * @return a list of incomplete orders
     */
    public LinkedList<Transaction> getOrders() {
        return new LinkedList<Transaction>();
    }
}