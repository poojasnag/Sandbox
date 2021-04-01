package com.sandbox.chat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.mgr.PlaceOrderMgr;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.R;
import com.sandbox.chat.utils.MultiRadio;

/**
 * Allows buyers to place their orders by providing order details
 *
 *
 * @author chua zi heng
 */
public class PlaceOrderActivity extends AppCompatActivity {
    private PlaceOrderMgr placeOrderController;


    private MultiRadio locationList;
    private Button submitButton;


    public void setSelectedLocation(String selectedLocation) {
        this.selectedLocation = selectedLocation;
    }

    private String selectedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        final BottomNavigationView bot_bar = findViewById(R.id.place_order_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        placeOrderController = new PlaceOrderMgr(this);
        submitButton = findViewById(R.id.place_order_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EaterySelectionMapActivity.class);
                startActivity(intent);
            }
        });

        locationList = findViewById(R.id.spinner2);
        placeOrderController.setLocationList(locationList);
        //get string array from source

    }



    @Override
    protected void onStart() {
        super.onStart();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeOrderController.submitOrder(view);
            }
        });

    }

    public Intent getPrevIntent() {
        return getIntent();
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public String getSelectedLocation() {
        return selectedLocation;
    }

}

