package com.sandbox.chat.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.R;
import com.sandbox.chat.mgr.EaterySelectionMapMgr;

/**
 * Displays the eatery selection interface
 */
public class EaterySelectionMapActivity extends AppCompatActivity {
    EaterySelectionMapMgr eaterySelectionMapController;
    public Intent i;

    /**
     * Initialize the interface
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatery_selection_map);
        eaterySelectionMapController = new EaterySelectionMapMgr(this);
        final BottomNavigationView bot_bar = findViewById(R.id.eatery_selection_botnav);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        Button next = findViewById(R.id.eatery_selection_tempButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eaterySelectionMapController.SelectLocation(view);
            }
        });
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        i = getIntent();
    }

    protected void SelectLocation(View view)
    {
        eaterySelectionMapController.SelectLocation(view);
    }

    public Intent getI() {
        return i;
    }
}