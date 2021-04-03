
package com.sandbox.chat.mgr;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sandbox.chat.models.Transaction;

import java.util.HashMap;
import java.util.Map;

public class TransactionMgr {
    private static FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private static String OFFER_TABLE = "transactions";
    public TransactionMgr(){}

    public static void setData(Transaction transaction, Context context){
        Map<String, Object> offer = new HashMap<>();
        offer.put("buyer", transaction.getBuyerID() );
        offer.put("delivererOfferID", transaction.getDelivererOfferID());
        offer.put("buyerLocation", transaction.getBuyerLocation());
        offer.put("orderDetails", transaction.getOrderDetails());
//        offer.put("buyerStatus", transaction.isBuyerStatus());
//        offer.put("delivererStatus", transaction.isDelivererStatus());
//        offer.put("orderStatus", transaction.isOrderStatus());


        String transactionID = transaction.getBuyerID() + '-' + transaction.getDelivererOfferID();
        DocumentReference documentReference = fStore.collection(OFFER_TABLE).document(transactionID);
        documentReference.set(offer).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid){
                Toast.makeText(context,"Data sent: " + transactionID, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,"Sending failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    public static Query getEateryDeliverers(Eatery eatery){
//        CollectionReference deliveryOffers_db = fStore.collection("deliveryOffers");
//        Query query = deliveryOffers_db.whereEqualTo("eatery", eatery);
//        return query;
//    }
}