package com.sandbox.chat.ui.presenter;

import com.sandbox.chat.ui.activities.PlaceOrderActivity;
import com.sandbox.chat.ui.contract.PlaceOrderContract;
import com.sandbox.chat.ui.fragments.PlaceOrderFragment;

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
        mPlaceOrderView.onSubmitSelect(new PlaceOrderFragment().getContext());
    }
}
