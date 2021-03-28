package com.sandbox.chat.mgr;

import android.content.Context;
import android.content.Intent;

import com.sandbox.chat.ui.activities.EaterySelectionMapActivity;

public class BnDMgr {
    public BnDMgr() {
    }

    public void onBuyerSelect(Context context){
        Intent intent = new Intent(context, EaterySelectionMapActivity.class);
        intent.putExtra("isBuyer", true);
        context.startActivity(intent);
    }
    public void onDelivererSelect(Context context)
    {
        Intent intent = new Intent(context, EaterySelectionMapActivity.class);
        intent.putExtra("isBuyer", false);
        context.startActivity(intent);
    }
}
