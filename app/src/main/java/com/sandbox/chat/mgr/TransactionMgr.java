
package com.sandbox.chat.mgr;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Deliverer;
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


//        String transactionID = transaction.getBuyerID() + '-' + transaction.getDelivererOfferID() + '-' + curTime;
        DocumentReference documentReference = fStore.collection(TRANSACTION_TABLE).document(transaction.getTransactionID());
        documentReference.set(offer).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid){
                Toast.makeText(context,"Data sent: " + transaction.getTransactionID(), Toast.LENGTH_SHORT).show();
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
            Log.e("transactionmgr", query.toString());
        }
        else{
            query = transaction_db.whereEqualTo("delivererID", uid).whereEqualTo("orderStatus", "PENDING");
        }
        return query;
    }
    public static void updateRating(boolean isComplete, String whichRating, String transactionID){
        CollectionReference transaction_db = fStore.collection(TRANSACTION_TABLE);
        if (isComplete){
            transaction_db.document(transactionID).update(whichRating, "COMPLETE");
            Log.e("updateRating", whichRating);
        }
        else{
            transaction_db.document(transactionID).update(whichRating, "INCOMPLETE");
        }
    }

    public static void orderRating(){

    }

}