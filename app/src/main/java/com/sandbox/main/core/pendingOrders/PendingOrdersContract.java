package com.sandbox.main.core.pendingOrders;

import androidx.recyclerview.widget.RecyclerView;

import com.sandbox.main.models.User;

/**
 * Contract interface for PendingOrdersActivity
 * @author Pooja Srinivas Nag
 * @author Mun Kei Wuai
 * @author Tan Wen Xiu
 */
public interface PendingOrdersContract {
    interface View {
        void getOrders(User user, Boolean isBuyer, RecyclerView ordersList);
    }
    interface Presenter {

    }
    interface onPendingOrdersListener {

    }
}
