package com.sandbox.chat.core.placeOrder;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.sandbox.chat.R;
import com.sandbox.chat.core.chooseDeliverer.ChooseDelivererContract;
import com.sandbox.chat.ui.activities.PlaceOrderActivity;
import com.sandbox.chat.ui.activities.UserRatingActivity;
import com.sandbox.chat.ui.fragments.PlaceOrderFragment;
import com.sandbox.chat.utils.MultiRadio;

public class PlaceOrderPresenter implements PlaceOrderContract.Presenter, PlaceOrderContract.OnPlaceOrderListener {
    private PlaceOrderContract.View mPlaceOrderView;
    private PlaceOrderInteractor mPlaceOrderInteractor;

    private Button mBtnEateryName, mBtnPlaceOrder;
    private EditText mETxtOrderDetails;
    private MultiRadio mMRadLocationList;

    private String selectedLocation;

    private PlaceOrderFragment placeOrderFragment;
    private PlaceOrderActivity placeOrderActivity;

    public PlaceOrderPresenter(PlaceOrderFragment placeOrderFragment){
        this.placeOrderFragment = placeOrderFragment;
    }

    public PlaceOrderPresenter(PlaceOrderActivity placeOrderActivity){
        this.placeOrderActivity = placeOrderActivity;
    }

    /**
     * Records a transaction to the database
     * @param view The button to which this function is bound
     */
    public void submitOrder(@NonNull View view) {

        //TODO: record new order
        Intent intent = new Intent(placeOrderFragment.getPrevIntent());
        //TODO: Connect to appropriate activity

//        intent.setComponent(new ComponentName(view.getContext(), PendingOrdersActivity.class));
        intent.setComponent(new ComponentName(view.getContext(), UserRatingActivity.class));
        placeOrderFragment.startActivity(intent);
    }

    public void setLocationList(MultiRadio locationList) {

        String[] locations = placeOrderFragment.getResources().getStringArray(R.array.deliver_to);
        //TODO: Replace string with selected locations by deliverer
        locationList.setItems(locations, "Select location", new MultiRadio.MultiRadioListener() {
            @Override
            public void onItemsSelected(int selected) {
                placeOrderFragment.setSelectedLocation(locationList.getItems(selected));
            }
        });
    }


    public Button getSubmitButton() {
        return mBtnPlaceOrder;
    }

}
