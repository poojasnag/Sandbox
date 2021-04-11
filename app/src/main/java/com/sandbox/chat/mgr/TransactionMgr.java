
package com.sandbox.chat.mgr;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sandbox.chat.adapters.OrderDetailsAdapter;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.Status;
import com.sandbox.chat.models.Transaction;
import com.sandbox.chat.models.User;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class TransactionMgr {
    private static FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private static String TRANSACTION_TABLE = "transactions";
    public TransactionMgr(){}


    public static void setData(Transaction transaction, Context context){
        Map<String, Object> offer = new HashMap<>();
        offer.put("buyerName", transaction.getBuyerName() );
        offer.put("delivererName", transaction.getDelivererName() );
        offer.put("buyerID", transaction.getBuyerID() );
        offer.put("delivererID", transaction.getBuyerID() );
        offer.put("delivererOfferID", transaction.getDelivererOfferID());
        offer.put("delivererID", transaction.getDelivererID());
        offer.put("buyerLocation", transaction.getBuyerLocation());
        offer.put("orderDetails", transaction.getOrderDetails());
        offer.put("orderStatus", transaction.isOrderStatus().name());
        offer.put("delivererStatus", transaction.isDelivererStatus().name());
        offer.put("buyerStatus", transaction.isBuyerStatus().name());
        offer.put("transactionID", transaction.getTransactionID());
        offer.put("eateryName", transaction.getEateryName());

//        String transactionID = transaction.getBuyerID() + '-' + transaction.getDelivererOfferID() + '-' + curTime;
        DocumentReference documentReference = fStore.collection(TRANSACTION_TABLE).document(transaction.getTransactionID());
        documentReference.set(offer).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid){
                Toast.makeText(context,"Data sent!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,"Sending failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static Query getTransactionHistory(String uid, Boolean isBuyer){
        CollectionReference transaction_db = fStore.collection(TRANSACTION_TABLE);
        Query query;
        if (isBuyer){
            query = transaction_db.whereEqualTo("buyerID", uid).whereEqualTo("orderStatus", "PENDING");

        }
        else{

            query = transaction_db.whereEqualTo("delivererID", uid).whereEqualTo("orderStatus", "PENDING");
        }
        return query;
    }

    public static Query getClosedOrders(String uid, Boolean isBuyer){
        CollectionReference transaction_db = fStore.collection(TRANSACTION_TABLE);
        Query query;
        if (isBuyer){
            query = transaction_db.whereEqualTo("buyerID", uid).whereEqualTo("orderStatus", "COMPLETE");

        }
        else{

            query = transaction_db.whereEqualTo("delivererID", uid).whereEqualTo("orderStatus", "COMPLETE");
        }
        return query;
    }
    public static void updateStatus(boolean isComplete, String whichStatus, String transactionID){
        CollectionReference transaction_db = fStore.collection(TRANSACTION_TABLE);
        if (isComplete){
            transaction_db.document(transactionID).update(whichStatus, "COMPLETE");
            checkOrderCompletion(transactionID);
        }
        else{
            transaction_db.document(transactionID).update(whichStatus, "INCOMPLETE");
            checkOrderCompletion(transactionID);
        }
    }

    public static void checkOrderCompletion(String transactionID){
        CollectionReference transaction_db = fStore.collection(TRANSACTION_TABLE);
        transaction_db.whereEqualTo("buyerStatus", "COMPLETE").whereEqualTo("delivererStatus", "COMPLETE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                fStore.collection(TRANSACTION_TABLE).document(document.getString("transactionID")).update("orderStatus", "COMPLETE");

                            }

                        }
                    }
                });

        transaction_db.whereEqualTo("buyerStatus", "INCOMPLETE").whereEqualTo("delivererStatus", "INCOMPLETE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                fStore.collection(TRANSACTION_TABLE).document(document.getString("transactionID")).update("orderStatus", "INCOMPLETE");

                            }

                        }
                    }
                });
    }

}