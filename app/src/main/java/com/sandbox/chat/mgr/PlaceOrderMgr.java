package com.sandbox.chat.mgr;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import com.sandbox.chat.PendingOrdersActivity;
import com.sandbox.chat.PlaceOrderActivity;

/**
 * Manager class for PlaceOrderActivity
 */
public class PlaceOrderMgr {
    private final PlaceOrderActivity placeOrderActivity;

    /**
     * Create a manager for a PlaceOrderActivity
     * @param placeOrderActivity
     */
    public PlaceOrderMgr(PlaceOrderActivity placeOrderActivity) {
        this.placeOrderActivity = placeOrderActivity;
    }

    /**
     * Records a transaction to the database
     * @param view The button to which this function is bound
     */
    public void submitOrder(@NonNull View view) {
        //TODO: record new order
        Intent intent = new Intent(placeOrderActivity.getPrevIntent());
        intent.setComponent(new ComponentName(view.getContext(), PendingOrdersActivity.class));
        placeOrderActivity.startActivity(intent);
    }
}