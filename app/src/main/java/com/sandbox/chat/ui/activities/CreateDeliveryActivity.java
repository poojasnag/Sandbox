package com.sandbox.chat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
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
import com.sandbox.chat.mgr.CreateDeliveryMgr;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * For deliverers, display the delivery offer creation interface
 */
public class CreateDeliveryActivity extends AppCompatActivity {


    Button createDeliveryButton;
    EditText cutoff_picker;
    EditText eta_picker;
    Spinner deliveryLocSpinner;
    EditText deliveryFeeText;
    /**
     * Initialize the interface.
     * Consisting of loading the corresponding layout file and binding the on-click listener to the navigation bar.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_delivery);

        createDeliveryButton = findViewById(R.id.create_delivery_send);
        cutoff_picker = findViewById(R.id.cutoff_datetime);
        eta_picker = findViewById(R.id.eta_datetime);
        deliveryLocSpinner = findViewById(R.id.create_delivery_select_location);
        deliveryFeeText = findViewById(R.id.create_delivery_fee);


    }

    @Override
    protected void onStart() {
        super.onStart();
        cutoff_picker.setInputType(InputType.TYPE_NULL);
        eta_picker.setInputType(InputType.TYPE_NULL);

        cutoff_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDeliveryMgr.showDateTimeDialog(cutoff_picker, v.getContext());
            }
        });

        eta_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDeliveryMgr.showDateTimeDialog(eta_picker, v.getContext());
            }
        });


        createDeliveryButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateDeliveryActivity.this, "create button clicked", Toast.LENGTH_SHORT).show();
                CreateDeliveryMgr.sendData(deliveryLocSpinner, deliveryFeeText, cutoff_picker, eta_picker, v.getContext());
            }
        });
//        Toast.makeText(CreateDeliveryActivity.this, "text:" + chosenLoc, Toast.LENGTH_SHORT).show();
        final BottomNavigationView bot_bar = findViewById(R.id.create_delivery_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

        //TODO: Pass selected location to header

    }





}

//    things to send to db
//        chosenLoc
