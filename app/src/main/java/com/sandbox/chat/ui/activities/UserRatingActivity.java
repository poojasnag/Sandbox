package com.sandbox.chat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.R;

//import com.sandbox.chat.mgr.UserRatingMgr;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.ui.fragments.UserRatingFragment;


// TODO: ThankYouUI

/**
 * Allows users to give rating to other users and provide comments if they wish
 *
 * @author chua zi heng
 */
public class UserRatingActivity extends AppCompatActivity {
    private Toolbar mToolbar; //pj

    public static void startActivity(Context context) { // pj
        Intent intent = new Intent(context, UserRatingActivity.class);
        Toast.makeText(context,"rating",Toast.LENGTH_SHORT).show();
        context.startActivity(intent);
    }

     @Override
    protected void onCreate(Bundle savedInstanceState) { //pj editted
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rating);
         final BottomNavigationView bot_bar = findViewById(R.id.choose_deliverer_bottomNavigationView);
         bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
//        userRatingController = new UserRatingMgr(this);
//        Button sendRating = findViewById(R.id.user_rating_submit);
//        sendRating.setOnClickListener(new View.OnClickListener() {
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

    public Intent getPrevIntent() {
        return getIntent();

    }

    private void init(){ //pj
        setSupportActionBar(mToolbar);

        // set the user rating screen fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_content_register,
                UserRatingFragment.newInstance(),
                UserRatingFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }
}

