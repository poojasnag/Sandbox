package com.sandbox.chat.mgr;

import java.util.LinkedList;

/**
 * Manager class for ClosedOrderActivity
 */
public class ClosedOrderMgr {

    public ClosedOrderMgr(){}

    public static LinkedList<String> orders;

    /**
     * Query the database for orders
     * @param buyerOrdersOnly Whether the user selected to view the orders where they were a buyer. Set to true when they click the "Buyer" option
     * @return a list of orders
     */
    public LinkedList<String> getOrders(boolean buyerOrdersOnly)
    {
        orders = new LinkedList<String>();
        orders.add("This is a plaaceholder closed order");
        //TODO: get the orders
        return orders;
    }
}