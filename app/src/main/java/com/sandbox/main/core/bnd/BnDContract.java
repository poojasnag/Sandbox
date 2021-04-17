package com.sandbox.main.core.bnd;

import android.content.Context;

/**
 * Contract interface for BnDActivity
 * @author Pooja Srinivas Nag
 * @author Mun Kei Wuai
 * @author Tan Wen Xiu
 */
public interface BnDContract {

    interface View {
        void onBuyerSelect(Context context);
        void onDelivererSelect(Context context);
    }
    interface Presenter {
    }
    interface onBnDListener {
        void onBuyerSuccess();
        void onDelivererSuccess();
    }
}
