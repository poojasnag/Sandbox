package com.sandbox.chat.core.userrating;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.sandbox.chat.ui.fragments.UserRatingFragment;

public class UserRatingPresenter implements UserRatingContract.Presenter, UserRatingContract.OnSubmitRatingListener{
    private UserRatingContract.View mUserRatingView;
    private UserRatingInteractor mUserRatingInteractor;

    public UserRatingPresenter(UserRatingContract.View userRatingView) {
        this.mUserRatingView = userRatingView;
        mUserRatingInteractor = new UserRatingInteractor(this);
    }

    @Override
    public void submitRating(Activity activity, int rating, String comment){
        System.out.println("UserRatingPresenter-submitRating");
        //mUserRatingInteractor.performFirebaseSubmitRating(activity, rating, comment);
    }
}
