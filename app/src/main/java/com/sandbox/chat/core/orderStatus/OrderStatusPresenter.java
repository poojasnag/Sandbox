package com.sandbox.chat.core.orderStatus;

import com.sandbox.chat.ui.activities.OrderStatusActivity;
import com.sandbox.chat.core.orderStatus.OrderStatusContract;

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
