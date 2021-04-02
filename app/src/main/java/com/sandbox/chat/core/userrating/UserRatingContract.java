package com.sandbox.chat.core.userrating;

import android.app.Activity;

public interface UserRatingContract {
    interface View {
        // success and fail function
    }

    interface Presenter {
        void submitRating(Activity activity, int rating, String comment);
    }

    interface Interactor{
        void performFirebaseSubmitRating(Activity activity, int rating, String comment);
    }

    interface OnSubmitRatingListener {
        // functions
    }
}
