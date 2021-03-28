package com.sandbox.chat.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.ui.adapters.OrderDetailsAdapter;
import com.sandbox.chat.R;

import java.util.LinkedList;
/**
 * This is a View class allows buyers and deliverers to see the pending orders unique to them
 *
 * @author chua zi heng
 */
public class PendingOrdersActivity extends AppCompatActivity {

    private LinkedList<String> orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_orders);

        final BottomNavigationView bot_bar = findViewById(R.id.pending_orders_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));


    }
    @Override
    public void onStart() {
        super.onStart();
        RecyclerView ordersList = findViewById(R.id.order_list);
        orders = new LinkedList<String>();
        orders.add("xxyyzz \t\t 21 Jan 2021\nDeliver to: hall 7\n Eatery: koi, Pioneer\nMilk Tea with Pearl");

        OrderDetailsAdapter adapter = new OrderDetailsAdapter(orders);
        //TODO: pass the list of orders to this adapter
        ordersList.setAdapter(adapter);
        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }

}

//TODO: Buyers and deliverers both will see pending orders, but the orders they see will be unique to them, need a Mgr