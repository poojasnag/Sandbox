package com.sandbox.main.core.placeOrder;

import com.sandbox.main.ui.activities.PlaceOrderActivity;
/**
 * Presenter class for PlaceOrdersActivity
 * @author Pooja Srinivas Nag
 * @author Mun Kei Wuai
 * @author Tan Wen Xiu
 */
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
