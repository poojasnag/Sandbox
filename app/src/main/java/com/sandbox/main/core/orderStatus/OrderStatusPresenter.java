package com.sandbox.main.core.orderStatus;

import com.sandbox.main.ui.activities.OrderStatusActivity;

/**
 * Presenter class for OrderStatusActivity
 * @author Pooja Srinivas Nag
 * @author Mun Kei Wuai
 * @author Tan Wen Xiu
 */
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
