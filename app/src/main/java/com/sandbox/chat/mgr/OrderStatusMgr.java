package com.sandbox.chat.mgr;

import com.sandbox.chat.models.Status;
import com.sandbox.chat.models.Transaction;

/**
 * Manager class for OrderStatusActivity
 */
public class OrderStatusMgr {
    public OrderStatusMgr(){}
    /**
     * Function to be bound to the "Chat with user" button
     * Redirects the user to the chat activity
     */
    public void chat()
    {

    }

    /**
     * Function to be called when the user updates the status of an order
     * Updates the details of the transaction in the database
     * @param transaction the transaction being viewed
     * @param status the new status set by the user
     */
    public void updateStatus(Transaction transaction, Status status)
    {

    }
}