package com.sandbox.chat.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sandbox.chat.R;
import com.sandbox.chat.adapters.OrderDetailsAdapter;
import com.sandbox.chat.mgr.TransactionMgr;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.Status;
import com.sandbox.chat.models.Transaction;
import com.sandbox.chat.models.User;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.ui.activities.PendingOrdersActivity;
import com.sandbox.chat.core.pendingOrders.PendingOrdersContract;
import com.sandbox.chat.core.pendingOrders.PendingOrdersPresenter;

import java.util.LinkedList;

/**
 * View containers for PendingOrdersActivity
 * @see PendingOrdersActivity
 */
public class PendingOrdersFragment extends Fragment implements PendingOrdersContract.View{
    private Intent intent;
    private ProgressDialog mProgressDialog;
//    private LinkedList<String> orders;
//    private LinkedList<Transaction> transactionList;
    private PendingOrdersActivity pendingOrdersActivity;
    private PendingOrdersPresenter pendingOrdersPresenter;


    public static PendingOrdersFragment newInstance() {
        Bundle args = new Bundle();
        PendingOrdersFragment fragment = new PendingOrdersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onStart() {
        super.onStart();


//        orders = new LinkedList<String>();  //TODO: get transaction objects
//        orders.add("xxyyzz \t\t 21 Jan 2021\nDeliver to: hall 7\n Eatery: koi, Pioneer\nMilk Tea with Pearl");
//        OrderDetailsAdapter adapter = new OrderDetailsAdapter(orders);
//        //TODO: pass the list of orders to this adapter
//        ordersList.setAdapter(adapter);
//        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_pending_orders, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    private void bindViews(View view) {
        RecyclerView ordersList = view.findViewById(R.id.order_list);
        final BottomNavigationView bot_bar = view.findViewById(R.id.pending_orders_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        intent = getActivity().getIntent();
        if (intent.getSerializableExtra("user") instanceof Buyer){
            getOrders((User)intent.getSerializableExtra("user"), true, ordersList);
        }

        else{
            getOrders((User)intent.getSerializableExtra("user"), false, ordersList);
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
    private void init() {
        pendingOrdersPresenter = new PendingOrdersPresenter((PendingOrdersActivity) getActivity());
        pendingOrdersActivity = (PendingOrdersActivity) getActivity();
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);
    }

    /**
     * Initializes the list of pending orders
     * @param user The current user
     * @param isBuyer Whether the current user is a buyer or not
     * @param ordersList The RecyclerView containing the orders
     */
    public void getOrders(User user, Boolean isBuyer, RecyclerView ordersList) { //TODO: only PENDING orders query

        LinkedList<Transaction> transactionList = new LinkedList<Transaction>();
        Log.e("userpending", user.getUid());
        TransactionMgr.getTransactionHistory(user.getUid(), isBuyer)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.e("document", document.getString("orderDetails"));
                                Transaction t = new Transaction(document.getString("eateryName"), document.getString("transactionID"), document.getString("buyerName"), document.getString("delivererName"), document.getString("buyerID"), document.getString("delivererOfferID"),
                                        document.getString("delivererID"), document.getString("buyerLocation"), document.getString("orderDetails"),
                                        Status.valueOf(document.getString("orderStatus")), Status.valueOf(document.getString("delivererStatus")),Status.valueOf(document.getString("buyerStatus"))
                                );
                                Log.e("pendingorderMgr", t.getBuyerID() + t.getOrderDetails() + t.isOrderStatus().toString());
                                transactionList.add(t);

                            }
                            Log.e("fulltransactionlist", transactionList.toString());
                            OrderDetailsAdapter ordersAdapter = new OrderDetailsAdapter(transactionList);

                            ordersList.setAdapter(ordersAdapter);
                            ordersList.setLayoutManager(new LinearLayoutManager(ordersList.getContext()));


                        }
                    }
                });
        if (isBuyer){
            Buyer buyer = new Buyer (user.getUid(), user.getEmail(), user.getFirebaseToken(),user.getRating(),user.getRatingCount(), transactionList); //TODO: mq - send to intent?
            Log.e("buyer", buyer.getEmail());
        }
        else{
            Deliverer deliverer = new Deliverer (user.getUid(), user.getEmail(), user.getFirebaseToken(),user.getRating(), user.getRatingCount(),transactionList); //TODO: mq - send to intent?
            Log.e("deliverer", deliverer.getEmail());

        }

    }


}