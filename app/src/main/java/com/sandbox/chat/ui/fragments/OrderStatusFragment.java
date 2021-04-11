package com.sandbox.chat.ui.fragments;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sandbox.chat.R;

import com.sandbox.chat.core.chat.ChatPresenter;

import com.sandbox.chat.interactors.DelivererOfferInteractor;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Status;
import com.sandbox.chat.models.Transaction;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.ui.activities.OrderCompleteActivity;
import com.sandbox.chat.ui.activities.OrderIncompleteActivity;
import com.sandbox.chat.ui.activities.OrderStatusActivity;
import com.sandbox.chat.ui.activities.UserListingActivity;
import com.sandbox.chat.core.orderStatus.OrderStatusContract;
import com.sandbox.chat.core.orderStatus.OrderStatusPresenter;

/**
 * View holder of OrderStatusActivity
 */
public class OrderStatusFragment extends Fragment implements View.OnClickListener, OrderStatusContract.View  {
    private OrderStatusActivity orderStatusActivity;
    private OrderStatusPresenter orderStatusPresenter;
    private ProgressDialog mProgressDialog;
    private ChatPresenter chatController;
    Intent i;
    private TextView partnerName;
    private TextView eta;
    private TextView rate;
    private TextView location;
    private TextView orderDetails;
    private Button eatery;
    private Button chat_button, complete_button, incomplete_button;

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
        chat_button = (Button) view.findViewById(R.id.order_status_chat);
        complete_button = (Button) view.findViewById(R.id.order_status_complete);
        incomplete_button = (Button) view.findViewById(R.id.order_status_incomplete);
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

        Transaction transaction = (Transaction) i.getSerializableExtra("Transaction");
        Log.e("orderstatus", transaction.isOrderStatus().toString());
        if (transaction.isOrderStatus().equals(Status.COMPLETE)){
            chat_button.setVisibility(View.INVISIBLE);
            complete_button.setVisibility(View.INVISIBLE);
            incomplete_button.setVisibility(View.INVISIBLE);
        }


        //TODO: This is potentially wrong

        if(i.getSerializableExtra("user") instanceof Buyer)
        {
            partnerName.setText("Deliverer Name: " +cur.getDelivererName());
        }
        else
        {
            partnerName.setText("Buyer Name: " +cur.getBuyerName());
        }

        Log.e("orderstatusFragment", cur.getEateryName());
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

        chat_button.setOnClickListener(this);
        complete_button.setOnClickListener(this);
        incomplete_button.setOnClickListener(this);
    }


//    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        switch (viewId) {
            case R.id.order_status_chat:
                onChatSelect(view.getContext());
                break;
            case R.id.order_status_complete:
                onCompleteSelect(view.getContext());
                break;
            case R.id.order_status_incomplete:
                onIncompleteSelect(view.getContext());
                break;
        }
    }

    /**
     * Called when the user selects "Chat with user"
     * Redirects the user to the UserListingActivity, where the user may select another user to chat with
     * @param context An instance of OrderStatusActivity
     */
    public void onChatSelect(Context context) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), "You have selected CHAT", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(i);
        intent.setComponent(new ComponentName(context, UserListingActivity.class));
        startActivity(intent);
    }

    /**
     * Called when the user clicks the "Order complete" button
     * Shows an order completion screen
     * @param context An instance of OrderStatusActivity
     * @see OrderCompleteActivity
     */
    public void onCompleteSelect(Context context) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), "You have selected COMPLETE ORDER", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(i);
        intent.setComponent(new ComponentName(context, OrderCompleteActivity.class));
        startActivity(intent);
    }

    /**
     * Called when the user clicks the "Order complete" button
     * Shows an order incomplete screen
     * @param context An instance of OrderStatusActivity
     * @see OrderIncompleteActivity
     */
    public void onIncompleteSelect(Context context) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), "You have selected INCOMPLETE ORDER", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(i);
        intent.setComponent(new ComponentName(context, OrderIncompleteActivity.class));
        startActivity(intent);
    }


    private void setDetails(Transaction cur){
        DelivererOfferInteractor.getDelivererOffer(cur.getDelivererOfferID()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                rate.setText("Delivery Fee: " + Double.toString(document.getDouble("deliveryFee")));
                                eta.setText("ETA : " + document.getString("etaDateTime"));
                                location.setText("Delivery Location: " +cur.getBuyerLocation());
                                eatery.setText("Eatery: " + cur.getEateryName());
                                orderDetails.setText("Order Details: " +cur.getOrderDetails());
                            }
                        }
                    }

                });
    }

}
