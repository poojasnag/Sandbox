package com.sandbox.chat.core.placeOrder;

import com.sandbox.chat.ui.activities.PlaceOrderActivity;
import com.sandbox.chat.core.placeOrder.PlaceOrderContract;

public class PlaceOrderPresenter implements PlaceOrderContract.Presenter, PlaceOrderContract.onPlaceOrderListener {

    private PlaceOrderActivity placeOrderActivity;
    private PlaceOrderContract.View mPlaceOrderView;
//    private placeOrderInteractor mPlaceOrderInteractor;

    public PlaceOrderPresenter() {}

    public PlaceOrderPresenter(PlaceOrderActivity placeOrderActivity) {
        this.placeOrderActivity = placeOrderActivity;
    }

    public PlaceOrderPresenter(PlaceOrderContract.View placeOrderView) {
        this.mPlaceOrderView = placeOrderView;
//        mLoginInteractor = new LoginInteractor(this);
    }

    @Override
    public void onSubmitSuccess() {
        mPlaceOrderView.onSubmitSelect(placeOrderActivity);
    }
}
