package com.sandbox.main.interactors;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.sandbox.main.models.Deliverer;
import com.sandbox.main.models.DelivererOffer;
import com.sandbox.main.models.Eatery;

import java.util.HashMap;
import java.util.Map;

/**
 * Control class for retrieving and sending data of deliverer offers
 * @author Chua Zi Heng
 */
public class DelivererOfferInteractor {
    private static FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private static String OFFER_TABLE = "delivererOffer";
    private static String TRANSACTION_TABLE = "transactions";
    public DelivererOfferInteractor(){}

    /**
     * Sends a deliverer offer to the database
     * @param delivererOffer The deliverer offer
     * @param context The current activity
     */
    public static void setData(DelivererOffer delivererOffer, Context context){
        Map<String, Object> offer = new HashMap<>();
        Deliverer deliverer = delivererOffer.getDeliverer();
        offer.put("email",deliverer.getEmail() );
        offer.put("firebaseToken",deliverer.getFirebaseToken() );
        offer.put("rating",deliverer.getRating());
        offer.put("ratingCount",deliverer.getRatingCount());
        offer.put("delivererID",deliverer.getUid());

        offer.put("deliveryLocation", delivererOffer.getDeliveryLocation());
        offer.put("deliveryFee", delivererOffer.getDeliveryFee());
        offer.put("cutoffDateTime", delivererOffer.getCutOffTime());
        offer.put("etaDateTime", delivererOffer.getEtaTime());
        offer.put("timestamp", delivererOffer.getTimestamp());
        offer.put("eatery", delivererOffer.getEatery());
        offer.put("delivererOfferID", delivererOffer.getDelivererOfferID());
        DocumentReference documentReference = fStore.collection(OFFER_TABLE).document(delivererOffer.getDelivererOfferID());
        documentReference.set(offer).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid){
                Toast.makeText(context,"Test sent: " + delivererOffer.getEatery().getEateryName(), Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,"Sending failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    public static Query getEateryDeliverers(Eatery eatery){
//        CollectionReference deliveryOffers_db = fStore.collection(OFFER_TABLE);
//        Query query = deliveryOffers_db.whereEqualTo("eatery", eatery);
//        return query;
//    }

    /**
     * Retrieves all deliverer offers for an eatery
     * @param eatery The eatery selected
     */
    public static Query getEateryDeliverers(Eatery eatery){
        CollectionReference deliveryOffers_db = fStore.collection(OFFER_TABLE);
        Query query = deliveryOffers_db.whereEqualTo("eatery", eatery);
        return query;
    }

    /**
     * Retrieves a deliverer offer by its ID
     * @param delivererOfferID The ID of the deliverer offer
     */

    public static Query getDelivererOffer(String delivererOfferID){
        CollectionReference deliveryOffers_db = fStore.collection(OFFER_TABLE);
        Query query = deliveryOffers_db.whereEqualTo("delivererOfferID", delivererOfferID);
        return query;
    }

}





