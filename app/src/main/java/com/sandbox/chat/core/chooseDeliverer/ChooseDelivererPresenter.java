package com.sandbox.chat.core.chooseDeliverer;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sandbox.chat.adapters.DelivererProfileAdapter;
import com.sandbox.chat.mgr.DelivererOfferMgr;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.ui.activities.ChooseDelivererActivity;
import com.sandbox.chat.utils.MultiSpinner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ChooseDelivererPresenter implements ChooseDelivererContract.Presenter {
    private ChooseDelivererContract.View mChooseDelivererView;
//    private ChooseDelivererInteractor mChooseDelivererInteractor;

    private static final String TAG = "ImportDB";
    private static final String KEY_LOCATION = "deliveryLocation";
    private static final String KEY_DELIVERYFEE = "deliveryFee";
    private static final String KEY_NAME = "email";
    private DelivererOffer delivererOffer;
    ChooseDelivererActivity chooseDelivererActivity;

    public ChooseDelivererPresenter(ChooseDelivererContract.View chooseDelivererView){
        this.mChooseDelivererView = chooseDelivererView;
//        mChooseDelivererInteractor = new ChooseDelivererInteractor(this);
    }

    // functions from contract - presenter

    public void getDeliverers(RecyclerView ordersList, Eatery eatery) {
        //TODO: change to actual deliverers

        LinkedList<DelivererOffer> demo = new LinkedList<DelivererOffer>();
        DelivererOfferMgr.getEateryDeliverers(eatery)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.e("deliverer", document.get("deliveryLocation").toString());
                                // document.getId() document.getData()
                                ArrayList<String> locationsList = (ArrayList<String>)document.get(KEY_LOCATION);
//                                DelivererOffer delivererOffer = document.toObject(DelivererOffer.class);   //TODO: try to implement this instead of hardcore creating Deliverer and DelivererOffer objects
                                Map delivererMap = ((Map) document.get("deliverer"));
                                Number deliveryFee = (Number) delivererMap.get("rating");

                                Deliverer deliverer = new Deliverer(delivererMap.get("uid").toString(),delivererMap.get("email").toString(), (delivererMap.get("firebaseToken") == null) ? "null" : delivererMap.get("firebaseToken").toString(), deliveryFee.floatValue(), null);
//                                Log.e("deliverer", deliverer.getUid());
                                 delivererOffer = new DelivererOffer(document.getString("delivererOfferID"), document.getString("cutOffTime"), document.getString("etaTime"), document.getDouble("deliveryFee"), locationsList, eatery, deliverer, document.getString("timestamp"));
//                                Log.e("delivereroffer",delivererOffer.toString());
                                demo.add(delivererOffer);
//                                demo.add(String.format("Name: %s \nRate:$%.2f\nLocations: %s", document.getString(KEY_NAME), document.getDouble(KEY_DELIVERYFEE), MultiSpinner.linkedListToString(locationsList)));
                            }
//                            Log.e("choosedelivererP", delivererOffer.getDeliverer().getEmail());
                            DelivererProfileAdapter adapter = new DelivererProfileAdapter(demo);

                            ordersList.setAdapter(adapter);
                            ordersList.setLayoutManager(new LinearLayoutManager(ordersList.getContext()));
                        } else {
                            Toast.makeText(chooseDelivererActivity, "Error getting documents", Toast.LENGTH_SHORT).show();
                        }
                    }


                });
    }

    public void setLocation (Button b, Intent i){
        b.setText(((Eatery)i.getSerializableExtra("Eatery")).getEateryName());
    }

    // functions from contract - onchoosedelivererlistener

}
