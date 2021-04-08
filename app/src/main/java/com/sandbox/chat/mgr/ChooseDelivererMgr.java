
package com.sandbox.chat.mgr;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.ui.activities.ChooseDelivererActivity;
import com.sandbox.chat.adapters.DelivererProfileAdapter;
import com.sandbox.chat.utils.MultiSpinner;

import java.util.LinkedList;
import java.util.List;

import io.perfmark.Link;

/**
 * Manager class for ChooseDelivererActivity
 */
public class ChooseDelivererMgr {
    private static final String TAG = "ImportDB";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_DELIVERYFEE = "deliveryFee";
    private static final String KEY_NAME = "email";
    FirebaseFirestore fStore;
    ChooseDelivererActivity chooseDelivererActivity;

    public ChooseDelivererMgr(ChooseDelivererActivity chooseDelivererActivity){
        this.chooseDelivererActivity = chooseDelivererActivity;
    }
    /**
     * Look up the database and get the list of deliverers
     */

    // goes under interpreter
//    public void getDeliverers(RecyclerView ordersList, Eatery eatery) {
//        //TODO: change to actual deliverers
//
//        LinkedList<String> demo = new LinkedList<String>();
//        DelivererOfferMgr.getEateryDeliverers(eatery)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
////                                        demo.add("Name: xxabcxx \nRate:$0.5\nLocation 1: Hall 7 bus stop");
////                                        demo.add("Name: test2 \nRate:$0.5\nLocation 1: Hall 7 bus stop");
////                                Toast.makeText(chooseDelivererActivity, document.getString(KEY_LOCATION), Toast.LENGTH_SHORT).show();
//                                // document.getId() document.getData()
////                                LinkedList<String> locationsList = (LinkedList <String>)document.get(KEY_LOCATION);
//                                DelivererOffer delivererOffer = document.toObject(DelivererOffer.class);
//                                Log.e("DELIVEREROFFER", delivererOffer.getClass().toString());
//                                demo.add(String.format("Name: %s \nRate:$%.2f\nLocations: %s", delivererOffer.getDeliverer().getEmail(), delivererOffer.getDeliveryFee(), MultiSpinner.linkedListToString(delivererOffer.getDeliveryLocation())));
////                                demo.add(String.format("Name: %s \nRate:$%.2f\nLocations: %s", document.getString(KEY_NAME), document.getDouble(KEY_DELIVERYFEE), MultiSpinner.linkedListToString(locationsList)));
//                            }
//                            DelivererProfileAdapter adapter = new DelivererProfileAdapter(demo);
//                            //TODO: pass the list of orders to this adapter
//                            ordersList.setAdapter(adapter);
//                            ordersList.setLayoutManager(new LinearLayoutManager(ordersList.getContext()));
//                        } else {
//                            Toast.makeText(chooseDelivererActivity, "Error getting documents", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//
//                });
//    }
//        old code gone
//        fStore = FirebaseFirestore.getInstance();
//        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
//
//        LinkedList<String> demo = new LinkedList<String>();
//        fStore.collection("deliveryOffers")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
////                                        demo.add("Name: xxabcxx \nRate:$0.5\nLocation 1: Hall 7 bus stop");
////                                        demo.add("Name: test2 \nRate:$0.5\nLocation 1: Hall 7 bus stop");
////                                Toast.makeText(chooseDelivererActivity, document.getString(KEY_LOCATION), Toast.LENGTH_SHORT).show();
//                                // document.getId() document.getData()
//                                demo.add(String.format("Name: %s \nRate:$%.2f\nLocation 1: %s", document.getString(KEY_NAME), document.getDouble(KEY_DELIVERYFEE), document.getString(KEY_LOCATION)));
//
//                            }
//                            DelivererProfileAdapter adapter = new DelivererProfileAdapter(demo);
//                            //TODO: pass the list of orders to this adapter
//                            ordersList.setAdapter(adapter);
//                            ordersList.setLayoutManager(new LinearLayoutManager(ordersList.getContext()));
//                        } else {
//                            Toast.makeText(chooseDelivererActivity, "Error getting documents" , Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//
//                });

    public void setLocation (Button b, Intent i){
        b.setText(((Eatery)i.getSerializableExtra("Eatery")).getEateryName());
    }
}
