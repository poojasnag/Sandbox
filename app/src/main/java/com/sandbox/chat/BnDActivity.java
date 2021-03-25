package com.sandbox.chat;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BnDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bn_d);

// TODO: Link the buttons on bottom nav bar with activities
        Button buyer_button = findViewById(R.id.bnd_button_buyer);
        Button deliverer_button = findViewById(R.id.bnd_button_deliverer);

    }

}