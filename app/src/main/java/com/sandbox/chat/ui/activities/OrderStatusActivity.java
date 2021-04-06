package com.sandbox.chat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.mgr.DelivererOfferMgr;
import com.sandbox.chat.mgr.OrderStatusMgr;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Eatery;
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
    OrderStatusMgr orderStatusController;
    private TextView partnerName;
    private TextView eta;
    private TextView rate;
    private TextView location;
    private TextView orderDetails;
    private Button eatery;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        orderStatusController = new OrderStatusMgr();
        final BottomNavigationView bot_bar = findViewById(R.id.order_status_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));


    }

    protected void onStart()
    {
        super.onStart();
        i = getIntent();
        bindViews();
    }
    protected void bindViews()
    {
        eatery = findViewById(R.id.order_status_location_header);
        partnerName = findViewById(R.id.order_status_buyer_name);
        rate = findViewById(R.id.order_status_rate);
        eta= findViewById(R.id.order_status_eta);
        orderDetails = findViewById(R.id.order_status_orders);
        location = findViewById(R.id.order_status_location_text);
        Transaction cur = (Transaction) i.getSerializableExtra("Transaction");


        //TODO: This is potentially wrong
        if(i.getSerializableExtra("user") instanceof Buyer)
        {
            partnerName.setText(cur.getDelivererID());
        }
        else
        {
            partnerName.setText(cur.getBuyerID());
        }
        //TODO: Zi Heng how do I access the delivererOffer from inside Transactions
        rate.setText("There is no rate in Transactions");
        //How do I get a delivererOffer from its ID
        eta.setText("There is also no ETA  in transactions");
        location.setText(cur.getBuyerLocation());
        eatery.setText("We also need a way to get the selected eatery from the transaction");
        orderDetails.setText(cur.getOrderDetails());

    }
}