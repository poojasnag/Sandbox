package com.sandbox.chat.mgr;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.sandbox.chat.R;
import com.sandbox.chat.ui.activities.PendingOrdersActivity;
import com.sandbox.chat.ui.activities.PlaceOrderActivity;
import com.sandbox.chat.utils.MultiRadio;

@Deprecated
/**
 * Manager class for PlaceOrderActivity
 */
public class PlaceOrderMgr {

//    private final PlaceOrderActivity placeOrderActivity;

    /**
     * Create a manager for a PlaceOrderActivity
     * @param placeOrderActivity
     */
//    public PlaceOrderMgr(PlaceOrderActivity placeOrderActivity) {
//        this.placeOrderActivity = placeOrderActivity;
//    }

    /**
     * Records a transaction to the database
     * @param view The button to which this function is bound
     */
//    public void submitOrder(@NonNull View view) {
//
//        //TODO: record new order
//        Intent intent = new Intent(placeOrderActivity.getPrevIntent());
//        intent.setComponent(new ComponentName(view.getContext(), PendingOrdersActivity.class));
//
//        placeOrderActivity.startActivity(intent);
//    }

//    public void setLocationList(MultiRadio locationList) {
//
//        String[] locations = placeOrderActivity.getResources().getStringArray(R.array.deliver_to);
//        //TODO: Replace string with selected locations by deliverer
//        locationList.setItems(locations, "Select location", new MultiRadio.MultiRadioListener() {
//            @Override
//            public void onItemsSelected(int selected) {
//                placeOrderActivity.setSelectedLocation(locationList.getItems(selected));
//            }
//        });
//    }


}