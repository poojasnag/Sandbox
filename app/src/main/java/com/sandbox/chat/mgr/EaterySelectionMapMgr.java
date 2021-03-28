package com.sandbox.chat.mgr;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;

import com.sandbox.chat.ChooseDelivererActivity;
import com.sandbox.chat.CreateDeliveryActivity;
import com.sandbox.chat.EaterySelectionMapActivity;

public class EaterySelectionMapMgr {
    private final EaterySelectionMapActivity eaterySelectionMapActivity;

    public EaterySelectionMapMgr(EaterySelectionMapActivity eaterySelectionMapActivity) {
        this.eaterySelectionMapActivity = eaterySelectionMapActivity;
    }

    public void SelectLocation(View view) {
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