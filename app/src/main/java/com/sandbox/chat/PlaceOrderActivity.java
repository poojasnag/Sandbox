package com.sandbox.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.mgr.PlaceOrderMgr;

/**
 * This is a View class that implements the BuyerOrderDetailsUI, which allows buyers to place their
 * orders by providing order details
 *
 *
 * @author chua zi heng
 */
public class PlaceOrderActivity extends AppCompatActivity {

    private final PlaceOrderMgr placeOrderMgr = new PlaceOrderMgr(this);

    Intent prevIntent;

    /**
     * Initialize the interface.
     * Consisting of loading the corresponding layout file and binding the on-click listener to the navigation bar.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        final BottomNavigationView bot_bar = findViewById(R.id.place_order_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));


        Button submitButton = findViewById(R.id.place_order_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeOrderMgr.submitOrder(view);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        prevIntent = getIntent();
    }

    public void submitOrder(@NonNull View view)
    {
        //TODO: record new order
        placeOrderMgr.submitOrder(view);
    }

    public Intent getPrevIntent() {
        return prevIntent;
    }
}

//TODO: PlaceOrderMgr to