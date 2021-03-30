package com.sandbox.chat.mgr;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sandbox.chat.R;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.ui.activities.ChooseDelivererActivity;
import com.sandbox.chat.ui.activities.CreateDeliveryActivity;
import com.sandbox.chat.ui.activities.EaterySelectionMapActivity;

/**
 * Manager class for EaterySelectionMapActivity
 */
public class EaterySelectionMapMgr {

    private final EaterySelectionMapActivity eaterySelectionMapActivity;

    /**
     * Create a manager for the activity
     * @param eaterySelectionMapActivity The activity that called this method
     */
    public EaterySelectionMapMgr(EaterySelectionMapActivity eaterySelectionMapActivity) {
        this.eaterySelectionMapActivity = eaterySelectionMapActivity;
    }

    public void showLocationDetails(View view)
    {

        TextView txtclose;
        Button btnFollow;
        Dialog myDialog = eaterySelectionMapActivity.getLocationDetails();
        myDialog.setContentView(R.layout.eatery_details);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("M");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
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
            eaterySelectionMapActivity.startActivity(intent);

        } else {
            Intent intent = new Intent(eaterySelectionMapActivity.getI());
            intent.setComponent(new ComponentName(view.getContext(), CreateDeliveryActivity.class));
            eaterySelectionMapActivity.startActivity(intent);
        }
    }
}