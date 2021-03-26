package com.sandbox.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.LinkedList;

public class ClosedOrderActivity extends AppCompatActivity {

    private LinkedList<String> orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed_order);
        final BottomNavigationView bot_bar = findViewById(R.id.closed_order_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        orders = new LinkedList<String>();
        orders.add("This is a plaaceholder closed order");
        RecyclerView ordersList = findViewById(R.id.closed_order_list);
        OrderDetailsAdapter adapter = new OrderDetailsAdapter(new LinkedList<String>());
        //TODO: pass the list of orders to this adapter
        ordersList.setAdapter(adapter);
        ordersList.setLayoutManager(new LinearLayoutManager(this));

    }
}