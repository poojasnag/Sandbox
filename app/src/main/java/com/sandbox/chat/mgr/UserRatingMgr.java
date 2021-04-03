//package com.sandbox.chat.mgr;
//
//import android.content.ComponentName;
//import android.content.Intent;
//import android.view.View;
//
//import com.sandbox.chat.ui.activities.EaterySelectionMapActivity;
//import com.sandbox.chat.ui.activities.UserRatingActivity;
//import com.sandbox.chat.ui.fragments.UserRatingFragment;
//
///**
// * Manager class for UserRatingActivity
// */
//public class UserRatingMgr {
//    private final UserRatingActivity userRatingActivity;
//
//    /**
//     * Create a manager for a UserRatingActivity
//     * @param userRatingActivity The activity that required the manager
//     */
//    public UserRatingMgr(UserRatingFragment userRatingActivity) {
//        this.userRatingActivity = userRatingActivity;
//    }
//
//    /**
//     * Records a rating to the database
//     * @param view The button which this function is bound to
//     */
//    public void submitRating(View view) {
//        Intent intent = new Intent(userRatingActivity.getPrevIntent());
//        intent.setComponent(new ComponentName(view.getContext(), EaterySelectionMapActivity.class));
//        userRatingActivity.startActivity(intent);
//    }
//}