package com.sandbox.chat.ui.contract;

import android.content.Context;

public interface BnDContract {

    interface View {
        void onBuyerSelect(Context context);
        void onDelivererSelect(Context context);
    }
    interface Presenter {
//       //TODO: add the info of whether user is buyer or del to db?
    }
    interface onBnDListener {
        void onBuyerSuccess();
        void onDelivererSuccess();
    }
}
