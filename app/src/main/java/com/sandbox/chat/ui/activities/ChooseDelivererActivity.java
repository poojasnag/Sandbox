package com.sandbox.chat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sandbox.chat.mgr.ChooseDelivererMgr;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.ui.BottomBarOnClickListener;

import com.sandbox.chat.R;
import com.sandbox.chat.adapters.DelivererProfileAdapter;

import java.util.LinkedList;

/**
 * For the buyers, displays the list of active deliverers in a location
 */
public class ChooseDelivererActivity extends AppCompatActivity {
    private Intent i;
    private static final String TAG = "ImportDB";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_DELIVERYFEE = "deliveryFee";
    private static final String KEY_NAME = "email";
    private Button selectedLocation;
    LinkedList<String> demo  = new LinkedList<String>();
    ChooseDelivererMgr chooseDelivererController;
    private RecyclerView ordersList;


    /**
     * Initialize the interface.
     * Consisting of loading the corresponding layout file and binding the on-click listener to the navigation bar.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_deliverer);
        chooseDelivererController = new ChooseDelivererMgr(this);
        final BottomNavigationView bot_bar = findViewById(R.id.choose_deliverer_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

        selectedLocation = findViewById(R.id.choose_deliverer_location);
        ordersList = findViewById(R.id.choose_deliverer_list);
    }

    /**
     * Populates the list with deliverers' information
     * Called directly after onCreate(), and whenever the user navigates to this activity from another activity
     */
    @Override
    protected void onStart()
    {
        super.onStart();
        i = getIntent();
        Eatery curEatery = (Eatery)(i.getSerializableExtra("Eatery"));
        chooseDelivererController.setLocation(selectedLocation, i);
        chooseDelivererController.getDeliverers(ordersList);

//        demo.add("Name: xxabcxx \nRate:$0.5\nLocation 1: Hall 7 bus stop");
//        demo.add("Name: test2 \nRate:$0.5\nLocation 1: Hall 7 bus stop");
//        Log.d(TAG, demo.toString());  // document.getId() document.getData()

    }
    public void setDelivererList(LinkedList<String> demo){
        this.demo = demo;
    }
}