package com.sandbox.chat.core.chooseDeliverer;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sandbox.chat.adapters.DelivererProfileAdapter;
import com.sandbox.chat.interactors.DelivererOfferInteractor;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.models.User;
import com.sandbox.chat.ui.activities.ChooseDelivererActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

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
    /**
     * Retrieves the delivery offers from the database
     * @param ordersList The RecyclerView which will contain the offers
     * @param eatery The method will query all offers delivering from this eatery
     */
    public void getDeliverers(RecyclerView ordersList, Eatery eatery, Intent i) {

        LinkedList<DelivererOffer> demo = new LinkedList<DelivererOffer>();
        DelivererOfferInteractor.getEateryDeliverers(eatery)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime now = LocalDateTime.now();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                ArrayList<String> locationsList = (ArrayList<String>)document.get(KEY_LOCATION);
                                Deliverer deliverer = new Deliverer(document.getString("delivererID") ,document.getString("email"),  (document.get("firebaseToken") == null) ? "null" : document.getString("firebaseToken"),   document.getLong("rating").intValue(), document.getLong("ratingCount").intValue(), null);
                                 delivererOffer = new DelivererOffer(document.getString("delivererOfferID"),document.getString("cutoffDateTime"), document.getString("etaDateTime"), document.getDouble("deliveryFee"), locationsList, eatery, deliverer, document.getString("timestamp"));
                                boolean delivererIsYou = ((User) i.getSerializableExtra("user")).getUid().equals(document.getString("delivererID")) ;

                                LocalDateTime cutoffDT = LocalDateTime.parse(delivererOffer.getCutOffTime(), f);
                                if (!delivererIsYou && !cutoffDT.isBefore(now)) demo.add(delivererOffer);
                            }

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
