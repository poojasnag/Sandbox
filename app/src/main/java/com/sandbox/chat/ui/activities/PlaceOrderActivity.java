package com.sandbox.chat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.sandbox.chat.mgr.PlaceOrderMgr;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.R;
import com.sandbox.chat.ui.fragments.ChooseDelivererFragment;
import com.sandbox.chat.utils.MultiRadio;

/**
 * Allows buyers to place their orders by providing order details
 *
 *
 * @author chua zi heng
 */
public class PlaceOrderActivity extends AppCompatActivity {
    private Toolbar mToolbar;

//    // RELOCATE
//    private MultiRadio locationList;
//    private Button submitButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        final BottomNavigationView bot_bar = findViewById(R.id.place_order_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        bindViews();
        init();

//        RELOCATE
//        placeOrderController = new PlaceOrderMgr(this);
//        submitButton = findViewById(R.id.place_order_submit);
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(view.getContext(), EaterySelectionMapActivity.class);
//                Intent intent = new Intent(view.getContext(), UserRatingActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        locationList = findViewById(R.id.spinner2);
//        placeOrderController.setLocationList(locationList);
//        //get string array from source

    }

    private void bindViews() { mToolbar = (Toolbar) findViewById(R.id.toolbar); }

    private void init(){
        // set the toolbar
        setSupportActionBar(mToolbar);

        // set the choose deliverer screen fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_content_place_order,
                ChooseDelivererFragment.newInstance(),
                ChooseDelivererFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

}

