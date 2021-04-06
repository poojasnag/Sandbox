package com.sandbox.chat.mgr;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sandbox.chat.adapters.DelivererProfileAdapter;
import com.sandbox.chat.adapters.OrderDetailsAdapter;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.models.Status;
import com.sandbox.chat.models.Transaction;
import com.sandbox.chat.models.User;

import java.util.LinkedList;

/**
 * Manager class for PendingOrdersActivity
 */
public class PendingOrdersMgr {
    public PendingOrdersMgr() {
    }

    /**
     * Retrieve the list of incomplete orders
     * @return a list of incomplete orders
     */
    public void getOrders(User user, Boolean isBuyer, RecyclerView ordersList) {

        LinkedList<Transaction> transactionList = new LinkedList<Transaction>();
        TransactionMgr.getTransactionHistory(user.getUid(), isBuyer)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.e("document", document.getString("orderDetails"));
                                Transaction t = new Transaction(document.getString("buyerID"), document.getString("delivererOfferID"),
                                        document.getString("delivererID"), document.getString("buyerLocation"), document.getString("orderDetails"),
                                        Status.valueOf(document.getString("orderStatus")), Status.valueOf(document.getString("delivererStatus")),Status.valueOf(document.getString("buyerStatus"))
                                );
                                Log.e("pendingorderMgr", t.getBuyerID() + t.getOrderDetails());
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
            Buyer buyer = new Buyer (user.getUid(), user.getEmail(), user.getFirebaseToken(),user.getRating(), transactionList); //TODO: mq - send to intent?
            Log.e("buyer", buyer.getEmail());
        }
        else{
            Deliverer deliverer = new Deliverer (user.getUid(), user.getEmail(), user.getFirebaseToken(),user.getRating(), transactionList); //TODO: mq - send to intent?
            Log.e("buyer", deliverer.getEmail());

        }

    }
}