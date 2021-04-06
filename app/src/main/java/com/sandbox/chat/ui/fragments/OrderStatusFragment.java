package com.sandbox.chat.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.R;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Transaction;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.ui.activities.OrderStatusActivity;
import com.sandbox.chat.ui.activities.PlaceOrderActivity;
import com.sandbox.chat.ui.presenter.OrderStatusPresenter;
import com.sandbox.chat.ui.presenter.PlaceOrderPresenter;


public class OrderStatusFragment extends Fragment {
    private OrderStatusActivity orderStatusActivity;
    private OrderStatusPresenter orderStatusPresenter;
    private ProgressDialog mProgressDialog;
    Intent i;
    private TextView partnerName;
    private TextView eta;
    private TextView rate;
    private TextView location;
    private TextView orderDetails;
    private Button eatery;

    public static OrderStatusFragment newInstance() {
        Bundle args = new Bundle();
        OrderStatusFragment fragment = new OrderStatusFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public void onStart()
    {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_order_status, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    private void bindViews(View view) {
        final BottomNavigationView bot_bar = view.findViewById(R.id.order_status_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

        i = getActivity().getIntent();
        Transaction cur = (Transaction) i.getSerializableExtra("Transaction");

        eatery = view.findViewById(R.id.order_status_location_header);
        partnerName = view.findViewById(R.id.order_status_buyer_name);
        rate = view.findViewById(R.id.order_status_rate);
        eta= view.findViewById(R.id.order_status_eta);
        orderDetails = view.findViewById(R.id.order_status_orders);
        location = view.findViewById(R.id.order_status_location_text);

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
//        Log.e("orderstatusactivity", ((Eatery) getIntent().getSerializableExtra("Eatery")).getEateryName());
        rate.setText("There is no rate in Transactions");
        //How do I get a delivererOffer from its ID
        eta.setText("There is also no ETA  in transactions");
        location.setText(cur.getBuyerLocation());
        eatery.setText("We also need a way to get the selected eatery from the transaction");
        orderDetails.setText(cur.getOrderDetails());
        Log.e("orderstatusfragment", );
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
    private void init() {
        orderStatusPresenter = new OrderStatusPresenter((OrderStatusActivity) getActivity());
        orderStatusActivity = (OrderStatusActivity) getActivity();
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

//        submitButton.setOnClickListener(this);
    }
}
