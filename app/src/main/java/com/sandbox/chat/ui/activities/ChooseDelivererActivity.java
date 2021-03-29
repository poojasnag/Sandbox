package com.sandbox.chat.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sandbox.chat.mgr.ChooseDelivererMgr;
import com.sandbox.chat.ui.BottomBarOnClickListener;

import com.sandbox.chat.R;

import java.util.LinkedList;

/**
 * For the buyers, displays the list of active deliverers in a location
 */
public class ChooseDelivererActivity extends AppCompatActivity {
    private static final String TAG = "ImportDB";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_DELIVERYFEE = "deliveryFee";
    private static final String KEY_NAME = "email";

    ChooseDelivererMgr chooseDelivererController;


    /**
     * Initialize the interface.
     * Consisting of loading the corresponding layout file and binding the on-click listener to the navigation bar.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_deliverer);
        chooseDelivererController = new ChooseDelivererMgr();
        final BottomNavigationView bot_bar = findViewById(R.id.choose_deliverer_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

    }

    /**
     * Populates the list with deliverers' information
     * Called directly after onCreate(), and whenever the user navigates to this activity from another activity
     */
    @Override
    protected void onStart()
    {
        super.onStart();
        RecyclerView ordersList = findViewById(R.id.choose_deliverer_list);

        chooseDelivererController.getDeliverers();
//        demo.add("Name: xxabcxx \nRate:$0.5\nLocation 1: Hall 7 bus stop");
//        demo.add("Name: test2 \nRate:$0.5\nLocation 1: Hall 7 bus stop");

    }
}