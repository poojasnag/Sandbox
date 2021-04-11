package com.sandbox.chat.core.orderComplete;

import android.content.Context;

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

