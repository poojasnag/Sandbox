package com.sandbox.main.core.pendingOrders;

import com.sandbox.main.ui.activities.PendingOrdersActivity;

/**
 * Presenter class for PendingOrdersActivity
 * @author Pooja Srinivas Nag
 * @author Mun Kei Wuai
 * @author Tan Wen Xiu
 */
public class PendingOrdersPresenter implements PendingOrdersContract.Presenter, PendingOrdersContract.onPendingOrdersListener {
    private PendingOrdersActivity pendingOrdersActivity;

    public PendingOrdersPresenter(){}

    public PendingOrdersPresenter(PendingOrdersActivity pendingOrdersActivity) {
        this.pendingOrdersActivity = pendingOrdersActivity;
    }
}
