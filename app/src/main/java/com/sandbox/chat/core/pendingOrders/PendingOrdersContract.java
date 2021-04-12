package com.sandbox.chat.core.pendingOrders;

import androidx.recyclerview.widget.RecyclerView;

import com.sandbox.chat.models.User;

public interface PendingOrdersContract {
    interface View {
        void getOrders(User user, Boolean isBuyer, RecyclerView ordersList);
    }
    interface Presenter {

    }
    interface onPendingOrdersListener {

    }
}
