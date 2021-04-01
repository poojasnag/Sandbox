package com.sandbox.chat.mgr;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.ui.activities.ChooseDelivererActivity;
import com.sandbox.chat.adapters.DelivererProfileAdapter;

import java.util.LinkedList;

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
    public void getDeliverers(RecyclerView ordersList, Eatery eatery) {
        //TODO: change to actual deliverers
        fStore = FirebaseFirestore.getInstance();
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();

        LinkedList<String> demo = new LinkedList<String>();
        CollectionReference deliveryOffers_db = fStore.collection("deliveryOffers");
        Query query = deliveryOffers_db.whereEqualTo("eatery", eatery);
        query
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                        demo.add("Name: xxabcxx \nRate:$0.5\nLocation 1: Hall 7 bus stop");
//                                        demo.add("Name: test2 \nRate:$0.5\nLocation 1: Hall 7 bus stop");
//                                Toast.makeText(chooseDelivererActivity, document.getString(KEY_LOCATION), Toast.LENGTH_SHORT).show();
                                // document.getId() document.getData()
                                demo.add(String.format("Name: %s \nRate:$%.2f\nLocation 1: %s", document.getString(KEY_NAME), document.getDouble(KEY_DELIVERYFEE), document.getString(KEY_LOCATION)));

                            }
                            DelivererProfileAdapter adapter = new DelivererProfileAdapter(demo);
                            //TODO: pass the list of orders to this adapter
                            ordersList.setAdapter(adapter);
                            ordersList.setLayoutManager(new LinearLayoutManager(ordersList.getContext()));
                        } else {
                            Toast.makeText(chooseDelivererActivity, "Error getting documents" , Toast.LENGTH_SHORT).show();
                        }
                    }


                });
    }
    public void setLocation(Button b, Intent i)
    {
        b.setText(((Eatery)i.getSerializableExtra("Eatery")).getEateryName());
    }

}
