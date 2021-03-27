package com.sandbox.chat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sandbox.chat.models.User;


import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


public class CreateDeliveryActivity extends AppCompatActivity {

    FirebaseFirestore fStore;
    Button createDeliveryButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_delivery);
        fStore = FirebaseFirestore.getInstance();

        // Show spinner choice
//        deliveryLocSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//                chosenLoc = parent.getItemAtPosition(pos).toString();
//                Toast.makeText(CreateDeliveryActivity.this, "Chosen Loc:" + chosenLoc, Toast.LENGTH_SHORT).show();
//            }
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });

        createDeliveryButton = findViewById(R.id.create_delivery_send);
        createDeliveryButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateDeliveryActivity.this, "create button clicked", Toast.LENGTH_SHORT).show();
                sendData();
            }
        });

//        Toast.makeText(CreateDeliveryActivity.this, "text:" + chosenLoc, Toast.LENGTH_SHORT).show();
        final BottomNavigationView bot_bar = findViewById(R.id.create_delivery_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

        //TODO: Pass selected location to header
        //TODO: Populate the lists of selectable timestamps
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void sendData(){
        long unixTime = Instant.now().getEpochSecond();
        String curTime = Long.toString(unixTime);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(CreateDeliveryActivity.this,"Current Time: " + curTime , Toast.LENGTH_SHORT).show();

        Spinner deliveryLocSpinner = findViewById(R.id.create_delivery_select_location);
        Spinner etaHourSpinner = findViewById(R.id.create_delivery_ETA_hour);
        Spinner etaMinSpinner = findViewById(R.id.create_delivery_ETA_min);
        Spinner etaAMPMSpinner = findViewById(R.id.am_or_pm);
        Spinner cutoffHourSpinner = findViewById(R.id.cutoff_time_hour);
        Spinner cutoffMinSpinner = findViewById(R.id.cutoff_time_min);
        Spinner cutoffAMPMSpinner = findViewById(R.id.cutoff_time_am_or_pm);
        EditText deliveryFeeText = findViewById(R.id.create_delivery_fee);

        String chosenLoc = deliveryLocSpinner.getSelectedItem().toString();
        String etaHour = etaHourSpinner.getSelectedItem().toString();
        String etaMin = etaMinSpinner.getSelectedItem().toString();
        String etaAMPM = etaAMPMSpinner.getSelectedItem().toString();
        String cutoffHour =  cutoffHourSpinner.getSelectedItem().toString();
        String cutoffMin =  cutoffMinSpinner.getSelectedItem().toString();
        String cutoffAMPM = cutoffAMPMSpinner.getSelectedItem().toString();
        String deliveryFee = deliveryFeeText.getText().toString();
        Toast.makeText(CreateDeliveryActivity.this,"ETA hour: " + etaHour, Toast.LENGTH_SHORT).show();

        if (user != null) {
            String uid = user.getUid();
            String offerID = curTime + "-" + uid;
            Map<String, Object> offer = new HashMap<>();
            offer.put("uid", uid);
            offer.put("location", chosenLoc);
            offer.put("etaHour", Integer.parseInt(etaHour));
            offer.put("etaMin", Integer.parseInt(etaMin));
            offer.put("etaAMPM", etaAMPM);
            offer.put("cutoffHour", Integer.parseInt(cutoffHour));
            offer.put("cutoffMin", Integer.parseInt(cutoffMin));
            offer.put("cutoffAMPM", cutoffAMPM);
            offer.put("deliveryFee", Double.parseDouble(deliveryFee));

            DocumentReference documentReference = fStore.collection("deliveryOffers").document(offerID);
            documentReference.set(offer).addOnSuccessListener(new OnSuccessListener<Void>(){
                @Override
                public void onSuccess(Void aVoid){
                    Toast.makeText(CreateDeliveryActivity.this,"Data sent: " + chosenLoc, Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CreateDeliveryActivity.this,"Sending failed", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(CreateDeliveryActivity.this, "No User Logged In", Toast.LENGTH_SHORT).show();
        }
    }

}

//    things to send to db
//        chosenLoc
