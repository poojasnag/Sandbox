package com.sandbox.chat.ui.presenter;

import com.sandbox.chat.ui.activities.PendingOrdersActivity;
import com.sandbox.chat.ui.activities.PlaceOrderActivity;
import com.sandbox.chat.ui.contract.PendingOrdersContract;
import com.sandbox.chat.ui.contract.PlaceOrderContract;

public class PendingOrdersPresenter implements PendingOrdersContract.Presenter, PendingOrdersContract.onPendingOrdersListener {
    private PendingOrdersActivity pendingOrdersActivity;

    public PendingOrdersPresenter(){}

    public PendingOrdersPresenter(PendingOrdersActivity pendingOrdersActivity) {
        this.pendingOrdersActivity = pendingOrdersActivity;
    }
}
