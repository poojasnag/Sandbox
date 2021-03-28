package com.sandbox.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sandbox.chat.mgr.UserRatingMgr;

// TODO: ThankYouUI
/**
 * This is a View class that implements the ThankYouUI, which allows users to give rating to other users
 * and provide comments if they wish
 *
 * @author chua zi heng
 */
public class UserRatingActivity extends AppCompatActivity {

    private final UserRatingMgr userRatingMgr = new UserRatingMgr(this);
    Intent prevIntent;

    /**
     * Initialize the interface.
     * Consisting of loading the corresponding layout file and binding the on-click listener to the navigation bar.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rating);
        Button sendRating = findViewById(R.id.user_rating_submit);
        sendRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRatingMgr.submitRating(view);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        prevIntent = getIntent();
    }

    public void submitRating(View view)
    {
        userRatingMgr.submitRating(view);
    }

    public Intent getPrevIntent() {
        return prevIntent;
    }
}

// TODO: Need to add a controller (ThankYOuMgr) to push the new user rating to database