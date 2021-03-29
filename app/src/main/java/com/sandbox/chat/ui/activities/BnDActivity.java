package com.sandbox.chat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sandbox.chat.R;
import com.sandbox.chat.mgr.BnDMgr;
import com.sandbox.chat.models.User;

/**
 * Displays the Buyer and Deliverer selection interface
 */

public class BnDActivity extends AppCompatActivity {
    BnDMgr bndController ;
    /**
     * Displays the interface from another activity class
     * @param context the Context of the activity that called this method
     */
    public static void startActivity(Context context, User user) {
        Intent intent = new Intent(context, BnDActivity.class);
        intent.putExtra("user", user);
        context.startActivity(intent);
    }

    /**
     * Displays the interface from another activity class
     * @param context the Context of the activity that called this method
     * @param flags flags to pass to the Intent before starting the activity
     */
    public static void startActivity(Context context, User user, int flags) {

        Intent intent = new Intent(context, BnDActivity.class);
        intent.putExtra("user", user);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    /**
     * Initialize the interface:
     *  + loading the corresponding layout file
     *  + Binding the on-click listener to the buttons.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bndController = new BnDMgr();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bn_d);

// TODO: Link the buttons on bottom nav bar with activities
        Button buyer_button = findViewById(R.id.bnd_button_buyer);
        Button deliverer_button = findViewById(R.id.bnd_button_deliverer);

        buyer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBuyerSelect();
            }
        });
        deliverer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onDelivererSelect();
            }
        });

    }

    private void onDelivererSelect() {
        Intent intent = new Intent(this.getIntent());
        intent.putExtra("user", bndController.createDeliverer((User) getIntent().getSerializableExtra("user")));
        startActivity(intent);
    }

    public void onBuyerSelect(){
        Intent intent = new Intent(this.getIntent());
        intent.putExtra("user", bndController.createBuyer((User) getIntent().getSerializableExtra("user")));
        startActivity(intent);
    }


}