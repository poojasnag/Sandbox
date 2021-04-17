package com.sandbox.main.core.orderComplete;

import android.content.Context;

/**
 * Contract interface for OrderCompleteActivity
 * @author Pooja Srinivas Nag
 * @author Mun Kei Wuai
 * @author Tan Wen Xiu
 */
public interface OrderCompleteContract {
    interface View {
        void onSelectRate(Context context);
        void updateStatus(Context context);
    }

    interface Presenter {
        //add function name here
    }

    interface onClickListener {

    }
}

