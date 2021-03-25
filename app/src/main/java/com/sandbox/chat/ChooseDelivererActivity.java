package com.sandbox.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChooseDelivererActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_deliverer);

        final BottomNavigationView bot_bar = findViewById(R.id.choose_deliverer_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
    }
}