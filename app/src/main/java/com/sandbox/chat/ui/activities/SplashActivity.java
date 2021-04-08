package com.sandbox.chat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.sandbox.chat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.sandbox.chat.core.maps.MapsInteractor;

import java.io.IOException;

import com.sandbox.chat.core.login.LoginPresenter;
import com.sandbox.chat.mgr.UserMgr;
import com.sandbox.chat.models.User;
import com.sandbox.chat.utils.Constants;
import com.sandbox.chat.utils.SharedPrefUtil;

/**
 * First screen when app is initialised, implements the splash UI
 *
 * @author chua zi heng
 */
public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_MS = 2000;
    private Handler mHandler;
    private Runnable mRunnable;
    MapsInteractor mapsInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        mapsInteractor = new MapsInteractor();

        try {
            MapsInteractor.initialize(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                // check if user is already logged in or not
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    // if logged in redirect the user to user listing activity
                    FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
                    UserMgr.getUserDocument(fUser.getUid())
                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                User curUser =  new User(fUser.getUid() ,fUser.getEmail(), new SharedPrefUtil(getApplicationContext()).getString(Constants.ARG_FIREBASE_TOKEN),  document.getLong("rating").intValue() , document.getLong("ratingCount").intValue()); //(int) document.get("rating"),(int)document.get("ratingCount")
                                BnDActivity.startActivity(SplashActivity.this, curUser);
                            }
                        }
                    });


                } else {
                    // otherwise redirect the user to login activity
                    LoginActivity.startIntent(SplashActivity.this);
                }
                finish();
            }
        };

        mHandler.postDelayed(mRunnable, SPLASH_TIME_MS);
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, SPLASH_TIME_MS);
    }*/
}
