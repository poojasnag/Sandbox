package com.sandbox.chat.mgr;

import android.content.Context;
import android.content.Intent;

import com.sandbox.chat.ui.activities.MapsActivity;

/**
 * Manager class for BnDActivity
 */
public class BnDMgr {

    public BnDMgr() {
    }
    /**
     * Implements the function to call when the user selects the "Buyer" option
     * @param context: The activity that called this function
     */
    public void onBuyerSelect(Context context){
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra("isBuyer", true);
        context.startActivity(intent);
    }
        /**
         * Implements the function to call when the user selects the "Buyer" option
         * @param context: The activity that called this function
         */
    public void onDelivererSelect(Context context)
    {
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra("isBuyer", false);
        context.startActivity(intent);
    }
}
