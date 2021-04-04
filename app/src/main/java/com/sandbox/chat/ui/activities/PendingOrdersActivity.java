package com.sandbox.chat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.mgr.PendingOrdersMgr;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.models.Transaction;
import com.sandbox.chat.models.User;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.adapters.OrderDetailsAdapter;
import com.sandbox.chat.R;

import java.util.LinkedList;

import io.perfmark.Link;

/**
 * Allows buyers and deliverers to see the pending orders that are unique to them
 *
 * @author chua zi heng
 */
public class PendingOrdersActivity extends AppCompatActivity {
    PendingOrdersMgr pendingOrdersController;
    private Intent intent;
    private LinkedList<String> orders;
    private LinkedList<Transaction> transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_orders);
        pendingOrdersController = new PendingOrdersMgr();
        final BottomNavigationView bot_bar = findViewById(R.id.pending_orders_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

    }
    @Override
    public void onStart() {
        super.onStart();
        intent = getIntent();
        RecyclerView ordersList = findViewById(R.id.order_list);
        if (intent.getSerializableExtra("user") instanceof Buyer){
            pendingOrdersController.getOrders((User)intent.getSerializableExtra("user"), true, ordersList);
        }
        else{
            pendingOrdersController.getOrders((User)intent.getSerializableExtra("user"), false, ordersList);
        }

//        orders = new LinkedList<String>();  //TODO: get transaction objects
//        orders.add("xxyyzz \t\t 21 Jan 2021\nDeliver to: hall 7\n Eatery: koi, Pioneer\nMilk Tea with Pearl");
//        OrderDetailsAdapter adapter = new OrderDetailsAdapter(orders);
//        //TODO: pass the list of orders to this adapter
//        ordersList.setAdapter(adapter);
//        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }

}

//TODO: Buyers and deliverers both will see pending orders, but the orders they see will be unique to them, need a Mgr