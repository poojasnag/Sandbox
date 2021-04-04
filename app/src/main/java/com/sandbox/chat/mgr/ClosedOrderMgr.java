package com.sandbox.chat.mgr;

import com.sandbox.chat.models.Transaction;

import java.util.LinkedList;

/**
 * Manager class for ClosedOrderActivity
 */
public class ClosedOrderMgr {

    public ClosedOrderMgr(){}

    public static LinkedList<Transaction> orders;

    /**
     * Query the database for orders
     * @param buyerOrdersOnly Whether the user selected to view the orders where they were a buyer. Set to true when they click the "Buyer" option
     * @return a list of orders
     */
    public LinkedList<Transaction> getOrders(boolean buyerOrdersOnly)
    {
        orders = new LinkedList<Transaction>();
        Transaction t = new Transaction(); //TODO: placeholder transaction
        orders.add(t);
        //TODO: get the orders
        return orders;
    }
}