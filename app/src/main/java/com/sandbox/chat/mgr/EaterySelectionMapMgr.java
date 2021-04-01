package com.sandbox.chat.mgr;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.maps.android.data.Feature;
import com.sandbox.chat.R;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.ui.activities.ChooseDelivererActivity;
import com.sandbox.chat.ui.activities.CreateDeliveryActivity;
import com.sandbox.chat.ui.activities.EaterySelectionMapActivity;

import java.io.IOException;

/**
 * Manager class for EaterySelectionMapActivity
 */
public class EaterySelectionMapMgr {

    private final EaterySelectionMapActivity eaterySelectionMapActivity;

    private EateryMgr eateryData;
    private Eatery curEatery;
    /**
     * Create a manager for the activity
     * @param eaterySelectionMapActivity The activity that called this method
     */
    public EaterySelectionMapMgr(EaterySelectionMapActivity eaterySelectionMapActivity) throws IOException, ClassNotFoundException {
        this.eaterySelectionMapActivity = eaterySelectionMapActivity;
        this.eateryData = new EateryMgr();
    }

    public void showLocationDetails(String feature_id)
    {

        String ID = feature_id;

        curEatery = eateryData.findEatery(ID);

        TextView txtclose;
        Button btnFollow;
        Dialog myDialog = eaterySelectionMapActivity.getLocationDetails();
        myDialog.setContentView(R.layout.eatery_details);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);



        TextView eateryName = myDialog.findViewById(R.id.eatery_name);
        TextView eateryLoc = myDialog.findViewById(R.id.eatery_addresss);
        TextView eateryTime = myDialog.findViewById(R.id.eatery_op_time);
        eateryName.setText(curEatery.getEateryName());
        eateryLoc.setText(curEatery.getEateryAddress() + ", " + curEatery.getEateryStreet());
        eateryTime.setText(curEatery.getOperatingTime());


        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLocation(view);
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    /**
     * Function to be called upon selecting a location
     * @param view
     */

    public void selectLocation(View view) {
        //TODO: Add the information of the location
//        if (eaterySelectionMapActivity.getI().getBooleanExtra("isBuyer", true)) {
        if (eaterySelectionMapActivity.getI().getSerializableExtra("user") instanceof Buyer){
            Intent intent = new Intent(eaterySelectionMapActivity.getI());
            intent.setComponent(new ComponentName(view.getContext(), ChooseDelivererActivity.class));
            if(intent.hasExtra("Eatery")) {
                intent.removeExtra("Eatery");
            }
            intent.putExtra("Eatery", curEatery);
            eaterySelectionMapActivity.startActivity(intent);

        } else {
            Intent intent = new Intent(eaterySelectionMapActivity.getI());
            intent.setComponent(new ComponentName(view.getContext(), CreateDeliveryActivity.class));
            if(intent.hasExtra("Eatery")) {
                intent.removeExtra("Eatery");
            }
            intent.putExtra("Eatery", curEatery);
            eaterySelectionMapActivity.startActivity(intent);
        }
    }
}