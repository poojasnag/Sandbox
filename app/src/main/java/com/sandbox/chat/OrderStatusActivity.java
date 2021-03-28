package com.sandbox.chat;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.models.Transaction;

/**
 * Displays the details of a selected order
 *
 * @author chua zi heng
 */
public class OrderStatusActivity extends AppCompatActivity {

    Transaction order;

    Intent prevIntent;

    /**
     * Initialize the interface.
     * Consisting of loading the corresponding layout file and binding the on-click listener to the navigation bar.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        final BottomNavigationView bot_bar = findViewById(R.id.order_status_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

    }

    @Override
    protected void onStart() {
        super.onStart();
        prevIntent = getIntent();
    }
}