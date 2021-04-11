package com.sandbox.chat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.sandbox.chat.R;
import com.sandbox.chat.ui.fragments.PlaceOrderFragment;
import com.sandbox.chat.ui.presenter.PlaceOrderPresenter;

/**
 * Allows buyers to place their orders by providing order details
 *
 *
 * @author chua zi heng
 */
public class PlaceOrderActivity extends AppCompatActivity {
    private PlaceOrderPresenter placeOrderController;
    //    private MultiRadio locationList;
//    private Button submitButton;
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
        placeOrderController = new PlaceOrderPresenter(this);
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
        fragmentTransaction.replace(R.id.frame_layout_content_place_order,
                PlaceOrderFragment.newInstance(),
                PlaceOrderFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }


}

