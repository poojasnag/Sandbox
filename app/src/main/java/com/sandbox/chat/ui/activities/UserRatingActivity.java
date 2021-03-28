package com.sandbox.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sandbox.chat.R;

import com.sandbox.chat.mgr.UserRatingMgr;

// TODO: ThankYouUI
/**
 * This is a View class that implements the ThankYouUI, which allows users to give rating to other users
 * and provide comments if they wish
 *
 * @author chua zi heng
 */
public class UserRatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rating);
        Button sendRating = findViewById(R.id.user_rating_submit);
        sendRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EaterySelectionMapActivity.class);
                startActivity(intent);
            }
        });
    }
}

// TODO: Need to add a controller (ThankYOuMgr) to push the new user rating to database