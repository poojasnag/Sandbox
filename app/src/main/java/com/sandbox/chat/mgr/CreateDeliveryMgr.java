package com.sandbox.chat.mgr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
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
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.models.Eatery;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Manager class for CreateDeliveryActivity
 */
public class CreateDeliveryMgr {
    public CreateDeliveryMgr(){}

    private static FirebaseFirestore fStore = FirebaseFirestore.getInstance();;

    /**
     *
     * @param date_time_in
     * @param context
     */

    /**
     * Records the delivery offer to the database
     * @param deliveryLocSpinner The drop-down list for delivery location
     * @param deliveryFeeText The text box for the delivery fee
     * @param cutoff_picker The text box for cutoff time
     * @param eta_picker The textbox for estimated time of arrival
     * @param context The activity that called this method
     */
    @RequiresApi(api = Build.VERSION_CODES.O)

    public void recordData(Spinner deliveryLocSpinner, EditText deliveryFeeText, EditText cutoff_picker, EditText eta_picker, Context context){
        long unixTime = Instant.now().getEpochSecond();
        String curTime = Long.toString(unixTime);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(context,"Current Time: " + curTime , Toast.LENGTH_SHORT).show();

        String chosenLoc = deliveryLocSpinner.getSelectedItem().toString();
        String deliveryFee = deliveryFeeText.getText().toString();
        String cutoffDateTime = cutoff_picker. getText().toString();
        String etaDateTime = eta_picker. getText().toString();
        ArrayList<String> placeholderLocations = new ArrayList<String>();
        placeholderLocations.add(chosenLoc); //TODO: create multiple choice for location
        Eatery eatery = new Eatery("Koi", 0,0); // TODO: create real eatery


        Toast.makeText(context,"ETA hour: " + cutoffDateTime, Toast.LENGTH_SHORT).show();
        Toast.makeText(context,"ETA hour: " + etaDateTime, Toast.LENGTH_SHORT).show();

        if (user != null) {
            String email = user.getEmail();
            String offerID = curTime + "-" + email;  //KEY: OfferID which is current time + uid (unique in every scenario)
//            DelivererOffer delivererOffer = new DelivererOffer(offerID,cutoffDateTime, etaDateTime, deliveryFee,placeholderLocations , eatery,  )
//            DelivererOfferMgr.setData();

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
                    Toast.makeText(context,"Data sent: " + chosenLoc, Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context,"Sending failed", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(context, "No User Logged In", Toast.LENGTH_SHORT).show();
        }
    }
}
