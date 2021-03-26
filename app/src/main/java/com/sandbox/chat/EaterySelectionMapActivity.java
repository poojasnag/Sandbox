package com.sandbox.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EaterySelectionMapActivity extends AppCompatActivity {
    public Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatery_selection_map);
        final BottomNavigationView bot_bar = findViewById(R.id.eatery_selection_botnav);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        Button next = findViewById(R.id.eatery_selection_tempButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectLocation(view);
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
        if(i.getBooleanExtra("isBuyer", true))
        {
            Intent intent = new Intent(i);
            intent.setComponent(new ComponentName(view.getContext(), ChooseDelivererActivity.class));
            startActivity(intent);

        }
        else
        {
            Intent intent = new Intent(i);
            intent.setComponent(new ComponentName(view.getContext(), CreateDeliveryActivity.class));
            startActivity(intent);
        }

    }

}