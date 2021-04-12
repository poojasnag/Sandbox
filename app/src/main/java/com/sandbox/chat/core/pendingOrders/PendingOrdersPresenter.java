package com.sandbox.chat.core.pendingOrders;

import com.sandbox.chat.ui.activities.PendingOrdersActivity;
import com.sandbox.chat.core.pendingOrders.PendingOrdersContract;

public class PendingOrdersPresenter implements PendingOrdersContract.Presenter, PendingOrdersContract.onPendingOrdersListener {
    private PendingOrdersActivity pendingOrdersActivity;

    public PendingOrdersPresenter(){}

    public PendingOrdersPresenter(PendingOrdersActivity pendingOrdersActivity) {
        this.pendingOrdersActivity = pendingOrdersActivity;
    }
}
