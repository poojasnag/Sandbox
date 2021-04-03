package com.sandbox.chat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.mgr.PlaceOrderMgr;
import com.sandbox.chat.models.User;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.R;
import com.sandbox.chat.ui.fragments.BnDFragment;
import com.sandbox.chat.ui.presenter.BnDPresenter;
import com.sandbox.chat.ui.presenter.PlaceOrderPresenter;
import com.sandbox.chat.utils.MultiRadio;

/**
 * Allows buyers to place their orders by providing order details
 *
 *
 * @author chua zi heng
 */
public class PlaceOrderActivity extends AppCompatActivity {
    private PlaceOrderPresenter placeOrderController;
    private MultiRadio locationList;
    private Button submitButton;
    private Toolbar mToolbar;
//    private String selectedLocation;

    /**
     * Displays the interface from another activity class
     *
     * @param context the Context of the activity that called this method
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PlaceOrderActivity.class);
        context.startActivity(intent);
    }

    /**
     * Displays the interface from another activity class
     *
     * @param context the Context of the activity that called this method
     * @param flags   flags to pass to the Intent before starting the activity
     */
    public static void startActivity(Context context, int flags) {

        Intent intent = new Intent(context, PlaceOrderActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    /**
     * Initialize the interface:
     * + loading the corresponding layout file
     * + Binding the on-click listener to the buttons.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        placeOrderController = new PlaceOrderPresenter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        bindViews();
        init();
    }

    private void bindViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void init() {
        // set the toolbar
        setSupportActionBar(mToolbar);

        // set the screen fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_content_bnd,
                BnDFragment.newInstance(),
                BnDFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }
}







//    public void setSelectedLocation(String selectedLocation) {
//        this.selectedLocation = selectedLocation;
//    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_place_order);
//        final BottomNavigationView bot_bar = findViewById(R.id.place_order_bottomNavigationView);
//        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
//
//        placeOrderController = new PlaceOrderPresenter(this);
//
//        submitButton = findViewById(R.id.place_order_submit);
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), EaterySelectionMapActivity.class);
//                startActivity(intent);
//            }
//        });

//        locationList = findViewById(R.id.spinner2);
//        placeOrderController.setLocationList(locationList);
        //get string array from source




//    @Override
//    protected void onStart() {
//        super.onStart();
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                placeOrderController.submitOrder(view);
//            }
//        });
//
//    }

//    public Intent getPrevIntent() {
//        return getIntent();
//    }
//
//    public Button getSubmitButton() {
//        return submitButton;
//    }
//
//    public String getSelectedLocation() {
//        return selectedLocation;
//    }


