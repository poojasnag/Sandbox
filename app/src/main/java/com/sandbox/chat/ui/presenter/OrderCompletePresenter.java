package com.sandbox.chat.ui.presenter;

import com.sandbox.chat.ui.activities.OrderCompleteActivity;

import com.sandbox.chat.ui.contract.OrderCompleteContract;


public class OrderCompletePresenter implements OrderCompleteContract.Presenter , OrderCompleteContract.onClickListener {
    private OrderCompleteActivity orderCompleteActivity;
    private OrderCompleteContract.View mOrderCompleteView ;
    //TODO: send the status of complete to db then add the function name to OrderCompleteContract.Presenter

    public OrderCompletePresenter(){}

    public OrderCompletePresenter(OrderCompleteActivity orderCompleteActivity){
        this.orderCompleteActivity = orderCompleteActivity;
    }
    public OrderCompletePresenter(OrderCompleteContract.View mOrderCompleteView){
        this.mOrderCompleteView = mOrderCompleteView;
    }
    public void updateOrderStatus(){
        mOrderCompleteView.updateStatus(orderCompleteActivity);
    }
}
