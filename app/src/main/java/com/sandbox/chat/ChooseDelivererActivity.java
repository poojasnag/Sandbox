package com.sandbox.chat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.LinkedList;

public class ChooseDelivererActivity extends AppCompatActivity {
    private static final String TAG = "ImportDB";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_DELIVERYFEE = "deliveryFee";
    private static final String KEY_NAME = "uid";
    LinkedList<String> demo  = new LinkedList<String>();

    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_deliverer);

        final BottomNavigationView bot_bar = findViewById(R.id.choose_deliverer_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        RecyclerView ordersList = findViewById(R.id.choose_deliverer_list);
        fStore = FirebaseFirestore.getInstance();


        fStore.collection("deliveryOffers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                        demo.add("Name: xxabcxx \nRate:$0.5\nLocation 1: Hall 7 bus stop");
//                                        demo.add("Name: test2 \nRate:$0.5\nLocation 1: Hall 7 bus stop");
                                Toast.makeText(ChooseDelivererActivity.this, document.getString(KEY_LOCATION), Toast.LENGTH_SHORT).show();
                                 // document.getId() document.getData()
                                demo.add(String.format("Name: %s \nRate:$%.2f\nLocation 1: %s", document.getString(KEY_NAME), document.getDouble(KEY_DELIVERYFEE), document.getString(KEY_LOCATION)));
                            }
                            DelivererProfileAdapter adapter = new DelivererProfileAdapter(demo);
                            //TODO: pass the list of orders to this adapter
                            ordersList.setAdapter(adapter);
                            ordersList.setLayoutManager(new LinearLayoutManager(ChooseDelivererActivity.this));
                        } else {
                            Toast.makeText(ChooseDelivererActivity.this, "Error getting documents" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//        demo.add("Name: xxabcxx \nRate:$0.5\nLocation 1: Hall 7 bus stop");
//        demo.add("Name: test2 \nRate:$0.5\nLocation 1: Hall 7 bus stop");
//        Log.d(TAG, demo.toString());  // document.getId() document.getData()


    }
}