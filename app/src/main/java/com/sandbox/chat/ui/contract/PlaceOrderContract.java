package com.sandbox.chat.ui.contract;

import android.content.Context;

public interface PlaceOrderContract {
    interface View {
        void onSubmitSelect(Context context);
    }
    interface Presenter {

    }

    interface onPlaceOrderListener {
        void onSubmitSuccess();
    }
}
