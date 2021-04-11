package com.sandbox.chat.core.closedOrder;

import com.sandbox.chat.ui.activities.ClosedOrderActivity;
import com.sandbox.chat.ui.activities.PendingOrdersActivity;

public class ClosedOrderPresenter {
    private ClosedOrderActivity closedOrderActivity;

    public ClosedOrderPresenter(){}

    public ClosedOrderPresenter(ClosedOrderActivity closedOrderActivity) {
        this.closedOrderActivity = closedOrderActivity;
    }
}
