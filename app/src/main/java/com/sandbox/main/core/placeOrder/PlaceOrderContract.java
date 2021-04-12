package com.sandbox.main.core.placeOrder;

import android.content.Context;

/**
 * Contract class for PlaceOrderActivity
 * @author Pooja Srinivas Nag
 * @author Mun Kei Wuai
 * @author Tan Wen Xiu
 */
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
