package com.sandbox.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.R;
import com.sandbox.chat.models.Transaction;

import java.util.LinkedList;
/**
 * Users are directed to this view after the buyer places their order, where users can view their order summary, chat with the other user, and indicate completion
 *
 * @author chua zi heng
 */
public class OrderStatusActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        final BottomNavigationView bot_bar = findViewById(R.id.order_status_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));


    }
}