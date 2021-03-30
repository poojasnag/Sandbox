package com.sandbox.chat.core.userrating;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserRatingInteractor implements UserRatingContract.Interactor{
    private static final String TAG = UserRatingInteractor.class.getSimpleName();
    private UserRatingContract.OnSubmitRatingListener mOnSubmitRatingListener;

    public UserRatingInteractor(UserRatingContract.OnSubmitRatingListener onSubmitRatingListener) {
        this.mOnSubmitRatingListener = onSubmitRatingListener;
    }

    @Override
    public void performFirebaseSubmitRating(Activity activity, int rating, String comment) {
//        FirebaseAuth.getInstance();
//        @Override
//        public void onComplete(){}
    }
}
