package com.sandbox.chat.ui.contract;

import android.content.Context;

public interface BnDContract {

    interface View {
        void onBuyerSelect(Context context);
        void onDelivererSelect(Context context);
    }
    interface Presenter {
//        void addRoleToDB();
    }
    interface onBnDListener {
        void onBuyerSuccess();
        void onDelivererSuccess();
    }
}