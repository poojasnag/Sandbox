package com.sandbox.chat.ui.activities;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.libraries.maps.GoogleMap;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.mgr.SettingsMgr;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.R;
import com.sandbox.chat.ui.fragments.RegisterFragment;
import com.sandbox.chat.ui.fragments.SettingsFragment;

/**
 * Allows users to customise their App, such as changing the app font
 *
 * @author chua zi heng
 */
public class SettingsActivity extends AppCompatActivity {


    SettingsMgr settingsController;
    private static int mapType;
    private Toolbar mToolbar;

    /**
     * Initialize the interface.
     * Consisting of loading the corresponding layout file and binding the on-click listener to the navigation bar.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        settingsController = new SettingsMgr();
        settingsController.changeTextSize();
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

