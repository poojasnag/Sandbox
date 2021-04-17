package com.sandbox.main.ui.activities;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sandbox.main.R;
import com.sandbox.main.core.maps.MapsInteractor;

/**
 * First screen when app is initialised, display the splash UI
 *
 * @author https://github.com/delaroy
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

        mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                // check if user is already logged in or not
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    // if logged in redirect the user to user listing activity
                    FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
                    BnDActivity.startActivity(SplashActivity.this, fUser);

                } else {
                    // otherwise redirect the user to login activity
                    LoginActivity.startIntent(SplashActivity.this);
                }

            }
        };

        mHandler.postDelayed(mRunnable, SPLASH_TIME_MS);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
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
