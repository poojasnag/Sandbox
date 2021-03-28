package com.sandbox.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.mgr.ClosedOrderMgr;

import java.util.LinkedList;

/**
 * Displays the interface for all finished orders
 */
public class ClosedOrderActivity extends AppCompatActivity {

    private final ClosedOrderMgr closedOrderMgr = new ClosedOrderMgr();

    @Override
    /**
     * Initialize the interface.
     * Consisting of loading the corresponding layout file and binding the on-click listener to the navigation bar.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed_order);
        final BottomNavigationView bot_bar = findViewById(R.id.closed_order_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

    }
    /**
     * Populates the list with information of the closed orders
     * Called directly after onCreate(), and whenever the user navigates to this activity from another activity
     */
    @Override
    protected void onStart()
    {
        super.onStart();
        RecyclerView ordersList = findViewById(R.id.closed_order_list);
        OrderDetailsAdapter adapter = new OrderDetailsAdapter(ClosedOrderMgr.getOrders());
        //TODO: pass the list of orders to this adapter
        ordersList.setAdapter(adapter);
        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }
}