package com.sandbox.chat.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.adapters.OrderDetailsAdapter;
import com.sandbox.chat.R;
import com.sandbox.chat.mgr.ClosedOrderMgr;

/**
 * Displays the interface for all finished orders
 */
public class ClosedOrderActivity extends AppCompatActivity {

    ClosedOrderMgr closedOrderController;

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
        closedOrderController = new ClosedOrderMgr();

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
        OrderDetailsAdapter adapter = new OrderDetailsAdapter(closedOrderController.getOrders(true));
        //TODO: pass the list of orders to this adapter
        ordersList.setAdapter(adapter);
        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }
}