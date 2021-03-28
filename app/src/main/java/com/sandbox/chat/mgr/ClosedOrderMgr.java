package com.sandbox.chat.mgr;

import java.util.LinkedList;

public class ClosedOrderMgr {
    public static LinkedList<String> orders;

    public static LinkedList<String> getOrders()
    {
        orders = new LinkedList<String>();
        orders.add("This is a plaaceholder closed order");
        //TODO: get the orders
        return orders;
    }
}