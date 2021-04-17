package com.sandbox.main.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.sandbox.main.R;
import com.sandbox.main.ui.fragments.SettingsFragment;

/**
 * Allows users to customise their App, such as changing the map style
 *
 * @author Luong Minh Quang
 */
public class SettingsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }

    private void bindViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void init() {
        // set the toolbar
        setSupportActionBar(mToolbar);
        // set the settings screen fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_content_settings,
                SettingsFragment.newInstance(),
                SettingsFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }







}

