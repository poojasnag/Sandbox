package com.sandbox.main.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.sandbox.main.R;
import com.sandbox.main.core.userRating.UserRatingPresenter;
import com.sandbox.main.ui.fragments.UserRatingFragment;

//import com.sandbox.chat.mgr.UserRatingMgr;


// TODO: ThankYouUI

/**
 * Allows users to give rating to other users and provide comments if they wish
 *
 * @author chua zi heng
 */
public class UserRatingActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private UserRatingPresenter userRatingController;
    private Button send_rating;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, UserRatingActivity.class);

        context.startActivity(intent);
    }

    public static void startActivity(Context context, int flags) {
        Intent intent = new Intent(context, UserRatingActivity.class);
//        intent.putExtra("user", user);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

     @Override
    protected void onCreate(Bundle savedInstanceState) { //pj editted
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rating);

//        userRatingController = new UserRatingPresenter((UserRatingContract.View) this);
//        send_rating = findViewById(R.id.user_rating_submit);
//        send_rating.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), EaterySelectionMapActivity.class);
//                startActivity(intent);
//            }
//        });
         bindViews();
         init();
    }

    private void bindViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    } // pj


    private void init(){
        setSupportActionBar(mToolbar);

        // set the user rating screen fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_content_user_rating,
                UserRatingFragment.newInstance(),
                UserRatingFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }
}

