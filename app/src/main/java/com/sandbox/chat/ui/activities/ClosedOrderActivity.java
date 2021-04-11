package com.sandbox.chat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.adapters.OrderDetailsAdapter;
import com.sandbox.chat.R;

import com.sandbox.chat.ui.fragments.ClosedOrderFragment;
import com.sandbox.chat.ui.fragments.OrderStatusFragment;
import com.sandbox.chat.ui.presenter.ClosedOrderPresenter;

/**
 * Displays the interface for all finished orders
 */
public class ClosedOrderActivity extends AppCompatActivity {
    ClosedOrderPresenter closedOrderPresenter;
    private Toolbar mToolbar;
    private Intent i;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ClosedOrderActivity.class);
        context.startActivity(intent);
    }
    public static void startActivity(Context context, int flags) {

        Intent intent = new Intent(context, ClosedOrderActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        closedOrderPresenter = new ClosedOrderPresenter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed_order);
        bindViews();
        init();
    }


    protected void bindViews()
    {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }
    private void init() {
        // set the toolbar
        setSupportActionBar(mToolbar);

        // set the screen fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_content_closed_order,
                ClosedOrderFragment.newInstance(),
                ClosedOrderFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }
//    @Override
//    protected void onStart()
//    {
//        super.onStart();
//        RecyclerView ordersList = findViewById(R.id.closed_order_list);
//        OrderDetailsAdapter adapter = new OrderDetailsAdapter(closedOrderController.getOrders(true));
//        //TODO: pass the list of orders to this adapter
//        ordersList.setAdapter(adapter);
//        ordersList.setLayoutManager(new LinearLayoutManager(this));
//    }
}