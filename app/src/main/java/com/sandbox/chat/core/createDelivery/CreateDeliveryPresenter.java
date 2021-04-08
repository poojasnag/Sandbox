package com.sandbox.chat.core.createDelivery;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.models.User;
import com.sandbox.chat.ui.activities.CreateDeliveryActivity;
import com.sandbox.chat.ui.fragments.CreateDeliveryFragment;
import com.sandbox.chat.utils.MultiSpinner;

import java.util.ArrayList;

public class CreateDeliveryPresenter implements CreateDeliveryContract.Presenter {
    private CreateDeliveryContract.View mCreateDeliveryView; // ok
    private CreateDeliveryActivity createDeliveryActivity;
    // private CreateDeliveryInteractor mCreateDeliveryInteractor; // add next time

    public CreateDeliveryPresenter() {}

    public CreateDeliveryPresenter(CreateDeliveryActivity createDeliveryActivity){
        this.createDeliveryActivity = createDeliveryActivity;
    }

    public CreateDeliveryPresenter(CreateDeliveryContract.View createDeliveryView){
        this.mCreateDeliveryView = createDeliveryView;
//        mCreateDeliveryInteractor = new CreateDeliveryInteractor(this);
    }

    // 1. add functions where the presenter calls the view
    public void onCreateBuyer(User user) {
        mCreateDeliveryView.createBuyer(user);
    }

    public void onCreateDeliverer(User user) {
        mCreateDeliveryView.createDeliverer(user);
    }

    @Override
    public void onRecordData(ArrayList<String> chosenLoc, double deliveryFee, String cutoffDateTime, String etaDateTime, Eatery eatery, Context context, Deliverer deliverer) {
        mCreateDeliveryView.recordData(chosenLoc, deliveryFee, cutoffDateTime, etaDateTime, eatery, context, deliverer);
    }

    @Override
    public void onSetLocation(Button b, Intent i) {
        mCreateDeliveryView.setLocation(b,i);
    }

    @Override
    public void onSetDeliveryLocations(MultiSpinner deliveryLocSpinner) {
        mCreateDeliveryView.setDeliveryLocations(deliveryLocSpinner);
    }

}
