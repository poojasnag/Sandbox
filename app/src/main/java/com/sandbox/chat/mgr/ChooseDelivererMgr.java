package com.sandbox.chat.mgr;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sandbox.chat.ChooseDelivererActivity;

import java.util.LinkedList;

public class ChooseDelivererMgr {
    private static final String TAG = "ImportDB";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_DELIVERYFEE = "deliveryFee";
    private static final String KEY_NAME = "email";

    public static LinkedList<String> getDeliverers(Context context) {
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();

        LinkedList<String> demo = new LinkedList<String>();
        fStore.collection("deliveryOffers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                        demo.add("Name: xxabcxx \nRate:$0.5\nLocation 1: Hall 7 bus stop");
//                                        demo.add("Name: test2 \nRate:$0.5\nLocation 1: Hall 7 bus stop");
                                Toast.makeText(context, document.getString(KEY_LOCATION), Toast.LENGTH_SHORT).show();
                                // document.getId() document.getData()
                                demo.add(String.format("Name: %s \nRate:$%.2f\nLocation 1: %s", document.getString(KEY_NAME), document.getDouble(KEY_DELIVERYFEE), document.getString(KEY_LOCATION)));

                            }

                        }
                    }


                });
        return demo;
    }
}
