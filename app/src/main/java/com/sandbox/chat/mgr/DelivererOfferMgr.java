package com.sandbox.chat.mgr;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.models.User;

import java.util.HashMap;
import java.util.Map;

public class DelivererOfferMgr {
    private static FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private static String OFFER_TABLE = "delivererOffers";
    DelivererOfferMgr(){}

    public static void setData(DelivererOffer delivererOffer, Context context){
        Map<String, Object> offer = new HashMap<>();
        Deliverer deliverer = delivererOffer.getDeliverer();
        offer.put("email",deliverer.getEmail() );
        offer.put("location", delivererOffer.getDeliveryLocation());
        offer.put("deliveryFee", delivererOffer.getDeliveryFee());
        offer.put("cutoffDateTime", delivererOffer.getCutOffTime());
        offer.put("etaDateTime", delivererOffer.getEtaTime());
        offer.put("timestamp", delivererOffer.getTimestamp());

        DocumentReference documentReference = fStore.collection(OFFER_TABLE).document(delivererOffer.getDelivererOfferID());
        documentReference.set(offer).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid){
                Toast.makeText(context,"Data sent: " + delivererOffer.getEtaTime(), Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,"Sending failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


}


