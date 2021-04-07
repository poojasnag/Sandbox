package com.sandbox.chat.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.sandbox.chat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.sandbox.chat.core.maps.MapsInteractor;

import java.io.IOException;

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
                    BnDActivity.startActivity(SplashActivity.this);
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
