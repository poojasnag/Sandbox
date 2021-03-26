package com.sandbox.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.LinkedList;

public class ChooseDelivererActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_deliverer);

        final BottomNavigationView bot_bar = findViewById(R.id.choose_deliverer_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        RecyclerView ordersList = findViewById(R.id.order_list);

        LinkedList<String> demo  = new LinkedList<String>();
        demo.add("Name: xxabcxx \nRate:$0.5\nLocation 1: Hall 7 bus stop");

        DelivererProfileAdapter adapter = new DelivererProfileAdapter(demo);
        //TODO: pass the list of orders to this adapter
        ordersList.setAdapter(adapter);
        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }
}