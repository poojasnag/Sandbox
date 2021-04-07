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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sandbox.chat.R;
import com.sandbox.chat.adapters.OrderDetailsAdapter;
import com.sandbox.chat.mgr.DelivererOfferMgr;
import com.sandbox.chat.mgr.UserMgr;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Eatery;
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
            partnerName.setText("Deliverer Name: " +cur.getDelivererID());
        }
        else
        {
            partnerName.setText("Buyer Name: " +cur.getBuyerID());
        }
        //TODO: Zi Heng how do I access the delivererOffer from inside Transactions
        Log.e("orderstatusFragment", ((Eatery) getActivity().getIntent().getSerializableExtra("Eatery")).getEateryName());

        setDetails(cur);



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
    private void setDetails(Transaction cur){
        DelivererOfferMgr.getDelivererOffer(cur.getDelivererOfferID()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                rate.setText("Delivery Fee: " + Double.toString(document.getDouble("deliveryFee")));
                                //How do I get a delivererOffer from its ID
                                eta.setText("ETA : " +document.getString("etaTime"));
                                location.setText("Delivery Location: " +cur.getBuyerLocation());
                                eatery.setText("Eatery: " +((Eatery) getActivity().getIntent().getSerializableExtra("Eatery")).getEateryName());
                                orderDetails.setText("Order Details: " +cur.getOrderDetails());
                            }
                        }
                    }

                });
    }
}
