package com.sandbox.chat.core.createDelivery;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.models.User;
import com.sandbox.chat.ui.activities.CreateDeliveryActivity;
import com.sandbox.chat.ui.fragments.CreateDeliveryFragment;
import com.sandbox.chat.utils.MultiSpinner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class CreateDeliveryPresenter implements CreateDeliveryContract.Presenter {
    private CreateDeliveryContract.View mCreateDeliveryView; // ok
    private CreateDeliveryActivity createDeliveryActivity;
    // private CreateDeliveryInteractor mCreateDeliveryInteractor; // add next time

    public CreateDeliveryPresenter() {}

    public CreateDeliveryPresenter(CreateDeliveryActivity createDeliveryActivity){
        this.createDeliveryActivity = createDeliveryActivity;
    }

    public CreateDeliveryPresenter(CreateDeliveryContract.View createDeliveryView){
        this.mCreateDeliveryView = createDeliveryView;
//        mCreateDeliveryInteractor = new CreateDeliveryInteractor(this);
    }

    // 1. add functions where the presenter calls the view
    public void onCreateBuyer(User user) {
        mCreateDeliveryView.createBuyer(user);
    }

    public void onCreateDeliverer(User user) {
        mCreateDeliveryView.createDeliverer(user);
    }

    @Override
    public void onRecordData(ArrayList<String> chosenLoc, double deliveryFee, String cutoffDateTime, String etaDateTime, Eatery eatery, Context context, Deliverer deliverer) {
        mCreateDeliveryView.recordData(chosenLoc, deliveryFee, cutoffDateTime, etaDateTime, eatery, context, deliverer);
    }

    // delivery fee between 0 and 20 (inclusive)
    // now < cutoff <= eta
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean validateCreateDelivery(String deliveryFee, String etaDateTime, String cutoffDateTime, ArrayList<String> selectedLocations){
        boolean isCorrectTime = false;
        boolean isCorrectFee = false;
        boolean isEmpty = deliveryFee.trim().equals("") || cutoffDateTime.trim().equals("") || etaDateTime.trim().equals("") || selectedLocations.size() ==0;
        Log.e("createactivitypresent", deliveryFee + etaDateTime + cutoffDateTime + " "+ String.valueOf(isEmpty));
        if (!isEmpty) {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime etaDT = LocalDateTime.parse(etaDateTime, f);
            Log.e("localdatetime", etaDT.toString());
            LocalDateTime cutoffDT = LocalDateTime.parse(cutoffDateTime, f);
            LocalDateTime now = LocalDateTime.now();
            isCorrectTime = !etaDT.isBefore(cutoffDT) && !cutoffDT.isBefore(now);
            isCorrectFee = (0f <= Float.valueOf(deliveryFee)) && (Float.valueOf(deliveryFee) <= 20f);  //set deliveryfee to be between 0 and 20
            Log.e("createactivitypresent", Float.valueOf(deliveryFee).toString() + " " + String.valueOf(isCorrectFee));
        }
        return  !isEmpty && isCorrectTime && isCorrectFee ;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String invalidateCreateDelivery(String deliveryFee, String etaDateTime, String cutoffDateTime, ArrayList<String> selectedLocations){
        boolean isCorrectTime = false;
        boolean isCorrectFee = false;
        boolean isEmpty = deliveryFee.trim().equals("") || cutoffDateTime.trim().equals("") || etaDateTime.trim().equals("") || selectedLocations.size() ==0;
        Log.e("createactivitypresent", deliveryFee + etaDateTime + cutoffDateTime + " "+ String.valueOf(isEmpty));
        String message = null;
        if (!isEmpty) {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime etaDT = LocalDateTime.parse(etaDateTime, f);
            Log.e("localdatetime", etaDT.toString());
            LocalDateTime cutoffDT = LocalDateTime.parse(cutoffDateTime, f);
            LocalDateTime now = LocalDateTime.now();
            isCorrectTime = !etaDT.isBefore(cutoffDT) && !cutoffDT.isBefore(now);
            isCorrectFee = (0f <= Float.valueOf(deliveryFee)) && (Float.valueOf(deliveryFee) <= 20f);  //set deliveryfee to be between 0 and 20
            if(!isCorrectTime && (isCorrectFee)){
                if(!etaDT.isBefore(cutoffDT) && cutoffDT.isBefore(now)){
                    message = "Please enter cut-off after current time.";
                }
                else if(etaDT.isBefore(cutoffDT) && !cutoffDT.isBefore(now)){
                     message = "Please enter an ETA after cut off time.";
                }
                else if(!isCorrectFee){
                    message = "Please enter a delivery fee between $0 and $20";
                }
            }
            Log.e("createactivitypresent", Float.valueOf(deliveryFee).toString() + " " + String.valueOf(isCorrectFee));
        }
        return message;
    }


    @Override
    public void onSetLocation(Button b, Intent i) {
        mCreateDeliveryView.setLocation(b,i);
    }

    @Override
    public void onSetDeliveryLocations(MultiSpinner deliveryLocSpinner) {
        mCreateDeliveryView.setDeliveryLocations(deliveryLocSpinner);
    }

}
