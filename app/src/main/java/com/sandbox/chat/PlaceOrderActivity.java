package com.sandbox.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * This is a View class that implements the BuyerOrderDetailsUI, which allows buyers to place their
 * orders by providing order details
 *
 *
 * @author chua zi heng
 */
public class PlaceOrderActivity extends AppCompatActivity {

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
                Intent intent = new Intent(view.getContext(), EaterySelectionMapActivity.class);
                startActivity(intent);
            }
        });
    }

}

//TODO: PlaceOrderMgr to