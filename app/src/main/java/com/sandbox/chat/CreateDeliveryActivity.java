package com.sandbox.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class CreateDeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_delivery);
        // Get spinner choice
        Spinner deliveryLocSpinner = findViewById(R.id.create_delivery_select_location);
        deliveryLocSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String chosenLoc = parent.getItemAtPosition(pos).toString();
                Toast.makeText(CreateDeliveryActivity.this, "Chosen Loc:" + chosenLoc, Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //TODO: Replace order cutoff time with same structure as ETA (zh: done)
        final BottomNavigationView bot_bar = findViewById(R.id.create_delivery_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        //TODO: Pass selected location to header
        //TODO: Populate the lists of selectable timestamps
    }



}

//    things to send to db
//        chosenLoc
