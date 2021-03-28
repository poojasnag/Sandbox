package com.sandbox.chat.mgr;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;

import com.sandbox.chat.ChooseDelivererActivity;
import com.sandbox.chat.CreateDeliveryActivity;
import com.sandbox.chat.EaterySelectionMapActivity;

/**
 * Manager class for EaterySelectionMapActivity
 */
public class EaterySelectionMapMgr {
    private final EaterySelectionMapActivity eaterySelectionMapActivity;

    /**
     * Create a manager for the activity
     * @param eaterySelectionMapActivity The activity that called this method
     */
    public EaterySelectionMapMgr(EaterySelectionMapActivity eaterySelectionMapActivity) {
        this.eaterySelectionMapActivity = eaterySelectionMapActivity;
    }

    /**
     * Function to be called upon selecting a location
     * @param view
     */
    public void SelectLocation(View view) {
        //TODO: Add the information of the location
        if (eaterySelectionMapActivity.getI().getBooleanExtra("isBuyer", true)) {
            Intent intent = new Intent(eaterySelectionMapActivity.getI());
            intent.setComponent(new ComponentName(view.getContext(), ChooseDelivererActivity.class));
            eaterySelectionMapActivity.startActivity(intent);

        } else {
            Intent intent = new Intent(eaterySelectionMapActivity.getI());
            intent.setComponent(new ComponentName(view.getContext(), CreateDeliveryActivity.class));
            eaterySelectionMapActivity.startActivity(intent);
        }

    }
}