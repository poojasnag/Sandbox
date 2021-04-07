package com.sandbox.chat.mgr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sandbox.chat.R;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.ui.activities.CreateDeliveryActivity;

import com.sandbox.chat.utils.MultiSpinner;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Manager class for CreateDeliveryActivity
 */
public class CreateDeliveryMgr {

    private CreateDeliveryActivity createDeliveryActivity;
    public CreateDeliveryMgr(CreateDeliveryActivity createDeliveryActivity) {
        this.createDeliveryActivity = createDeliveryActivity;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void recordData(ArrayList<String> locationsList, double deliveryFee, String cutoffDateTime, String etaDateTime, Eatery eatery, Context context, Deliverer deliverer) {
        long unixTime = Instant.now().getEpochSecond();
        String curTime = Long.toString(unixTime);

        if (deliverer != null) {
            String email = deliverer.getEmail();
            String offerID = curTime + "-" + email;  //KEY: OfferID which is current time + uid (unique in every scenario)
            DelivererOffer delivererOffer = new DelivererOffer(offerID, cutoffDateTime, etaDateTime, deliveryFee, locationsList, eatery, deliverer, curTime);
//            Toast.makeText(context, delivererOffer.getClass().getName(), Toast.LENGTH_SHORT).show();
            DelivererOfferMgr.setData(delivererOffer, context);
        }

    }
    public void setLocation(Button b, Intent i)
    {
        Eatery e = (Eatery) i.getSerializableExtra("Eatery");
        b.setText(e.getEateryName());

    }


}
