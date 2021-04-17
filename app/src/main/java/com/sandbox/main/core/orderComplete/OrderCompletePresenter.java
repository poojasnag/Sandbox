package com.sandbox.main.core.orderComplete;

import com.sandbox.main.ui.activities.OrderCompleteActivity;

/**
 * Presenter class for OrderCompleteActivity
 * @author Pooja Srinivas Nag
 * @author Mun Kei Wuai
 * @author Tan Wen Xiu
 */
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
