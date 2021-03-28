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

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class CreateDeliveryActivity extends AppCompatActivity {

    FirebaseFirestore fStore;
    Button createDeliveryButton;
    EditText cutoff_picker;
    EditText eta_picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_delivery);
        fStore = FirebaseFirestore.getInstance();
        createDeliveryButton = findViewById(R.id.create_delivery_send);
        cutoff_picker = findViewById(R.id.cutoff_datetime);
        eta_picker = findViewById(R.id.eta_datetime);

        cutoff_picker.setInputType(InputType.TYPE_NULL);
        eta_picker.setInputType(InputType.TYPE_NULL);

        cutoff_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(cutoff_picker);
            }
        });

        eta_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(eta_picker);
            }
        });


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

    private void showDateTimeDialog(final EditText date_time_in) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(CreateDeliveryActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(CreateDeliveryActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void sendData(){
        long unixTime = Instant.now().getEpochSecond();
        String curTime = Long.toString(unixTime);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(CreateDeliveryActivity.this,"Current Time: " + curTime , Toast.LENGTH_SHORT).show();

        Spinner deliveryLocSpinner = findViewById(R.id.create_delivery_select_location);
        EditText deliveryFeeText = findViewById(R.id.create_delivery_fee);

        String chosenLoc = deliveryLocSpinner.getSelectedItem().toString();
        String deliveryFee = deliveryFeeText.getText().toString();
        String cutoffDateTime = cutoff_picker. getText().toString();
        String etaDateTime = eta_picker. getText().toString();


        Toast.makeText(CreateDeliveryActivity.this,"ETA hour: " + cutoffDateTime, Toast.LENGTH_SHORT).show();
        Toast.makeText(CreateDeliveryActivity.this,"ETA hour: " + etaDateTime, Toast.LENGTH_SHORT).show();

        if (user != null) {
            String email = user.getEmail();
            String offerID = curTime + "-" + email;  //KEY: OfferID which is current time + uid (unique in every scenario)
            Map<String, Object> offer = new HashMap<>();
            offer.put("email", email);
            offer.put("location", chosenLoc);
            offer.put("deliveryFee", Double.parseDouble(deliveryFee));
            offer.put("cutoffDateTime", cutoffDateTime);
            offer.put("etaDateTime", etaDateTime);
            offer.put("timestamp", curTime);

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
