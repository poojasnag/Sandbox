package com.sandbox.chat.ui.presenter;

import com.sandbox.chat.ui.activities.OrderStatusActivity;
import com.sandbox.chat.ui.contract.OrderStatusContract;
import com.sandbox.chat.ui.contract.PlaceOrderContract;

public class OrderStatusPresenter {
    private OrderStatusActivity orderStatusActivity;
    private OrderStatusContract.View mOrderStatusView;

    public OrderStatusPresenter (){}
    public OrderStatusPresenter(OrderStatusActivity orderStatusActivity) {
        this.orderStatusActivity = orderStatusActivity;
    }

    public OrderStatusPresenter(OrderStatusContract.View orderStatusView) {
        this.mOrderStatusView = orderStatusView;
//        mLoginInteractor = new LoginInteractor(this);
    }




}
