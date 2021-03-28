package com.sandbox.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.LinkedList;

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