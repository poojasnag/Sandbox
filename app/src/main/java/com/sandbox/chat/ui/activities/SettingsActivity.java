package com.sandbox.chat.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.R;

/**
 * Allows users to customise their App, such as changing the app font
 *
 * @author chua zi heng
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final BottomNavigationView bot_bar = findViewById(R.id.settings_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
    }
}

