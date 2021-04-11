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
import com.sandbox.chat.models.Status;
import com.sandbox.chat.models.Transaction;
import com.sandbox.chat.models.User;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.ui.activities.ClosedOrderActivity;
import com.sandbox.chat.core.closedOrder.ClosedOrderPresenter;

import java.util.LinkedList;

/**
 * View holder of ClosedOrderActivity
 */
public class ClosedOrderFragment extends Fragment {
    private Intent intent;
    private ProgressDialog mProgressDialog;
    private ClosedOrderActivity closedOrderActivity;
    private ClosedOrderPresenter closedOrderPresenter;

    public static ClosedOrderFragment newInstance() {
        Bundle args = new Bundle();
        ClosedOrderFragment fragment = new ClosedOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onStart() {
        super.onStart();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_closed_order, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    private void bindViews(View view) {
        RecyclerView ordersList = view.findViewById(R.id.closed_order_list);
        final BottomNavigationView bot_bar = view.findViewById(R.id.closed_order_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        intent = getActivity().getIntent();
        if (intent.getSerializableExtra("user") instanceof Buyer){
            getClosedOrders((User)intent.getSerializableExtra("user"), true, ordersList);
        }
        else{
            getClosedOrders((User)intent.getSerializableExtra("user"), false, ordersList);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
    private void init() {
        closedOrderPresenter = new ClosedOrderPresenter((ClosedOrderActivity) getActivity());
        closedOrderActivity = (ClosedOrderActivity) getActivity();
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);
    }

    /**
     * Initializes the list of completed orders
     * @param user
     * @param isBuyer
     * @param ordersList
     */
    public void getClosedOrders(User user, Boolean isBuyer, RecyclerView ordersList) { //TODO: only PENDING orders query

        LinkedList<Transaction> transactionList = new LinkedList<Transaction>();
        TransactionMgr.getClosedOrders(user.getUid(), isBuyer)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Transaction t = new Transaction(document.getString("eateryName"), document.getString("transactionID"), document.getString("buyerName"), document.getString("delivererName"), document.getString("buyerID"), document.getString("delivererOfferID"),
                                        document.getString("delivererID"), document.getString("buyerLocation"), document.getString("orderDetails"),
                                        Status.valueOf(document.getString("orderStatus")), Status.valueOf(document.getString("delivererStatus")), Status.valueOf(document.getString("buyerStatus"))
                                );
                                Log.e("closedordersfrag", t.getBuyerID() + t.getOrderDetails());
                                transactionList.add(t);

                            }
                            Log.e("closedorderslist", transactionList.toString());
                            OrderDetailsAdapter ordersAdapter = new OrderDetailsAdapter(transactionList);

                            ordersList.setAdapter(ordersAdapter);
                            ordersList.setLayoutManager(new LinearLayoutManager(ordersList.getContext()));


                        }
                    }
                });

    }
}
