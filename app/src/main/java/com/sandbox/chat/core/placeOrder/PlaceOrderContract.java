package com.sandbox.chat.core.placeOrder;

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
