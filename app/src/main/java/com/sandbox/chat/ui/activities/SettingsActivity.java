package com.sandbox.chat.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.R;

/**
 * This is a View class that implements the SettingsUI, which allows users to customise their App,
 * for example to change the app font
 *
 * @author chua zi heng
 */
public class SettingsActivity extends AppCompatActivity {


    /**
     * Initialize the interface.
     * Consisting of loading the corresponding layout file and binding the on-click listener to the navigation bar.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final BottomNavigationView bot_bar = findViewById(R.id.settings_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
    }

}

//TODO: SettingsMgr