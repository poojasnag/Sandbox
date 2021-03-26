package com.sandbox.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CreateDeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_delivery);
        //TODO: Replace order cutoff time with same structure as ETA
        final BottomNavigationView bot_bar = findViewById(R.id.create_delivery_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        //TODO: Pass selected location to header
        //TODO: Populate the lists of selectable timestamps
    }
}