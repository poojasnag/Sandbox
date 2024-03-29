package com.sandbox.main.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.main.R;
import com.sandbox.main.ui.BottomBarOnClickListener;
import com.sandbox.main.ui.fragments.CreateDeliveryFragment;

//import com.sandbox.chat.mgr.CreateDeliveryMgr;

/**
 * For deliverers, display the delivery offer creation interface
 */
public class CreateDeliveryActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Intent i;
//    Button createDeliveryButton;
//    EditText cutoff_picker;
//    EditText eta_picker;
//    MultiSpinner deliveryLocSpinner;
//    EditText deliveryFeeText;
//    CreateDeliveryPresenter createDeliveryPresenter;
//    Button locationDisplay;
//    Intent prevIntent;
//    Eatery curEatery;

    // ok
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CreateDeliveryActivity.class);
        context.startActivity(intent);
    }

    // ok


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_delivery);

        final BottomNavigationView bot_bar = findViewById(R.id.create_delivery_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        bindViews();
        init();
    }

    // OK
    private void bindViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    // ok
    private void init() {
        // set the toolbar
        setSupportActionBar(mToolbar);

        // set the create delivery screen fragmentn
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_content_create_delivery,
                CreateDeliveryFragment.newInstance(),
                CreateDeliveryFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

}


    // remove / shift
//    @Override
//    protected void onStart() {
//
//        super.onStart();
//        prevIntent = getIntent();
//        cutoff_picker.setInputType(InputType.TYPE_NULL);
//        eta_picker.setInputType(InputType.TYPE_NULL);
//        curEatery = ((Eatery)prevIntent.getSerializableExtra("Eatery"));
//        createDeliveryController.setDeliveryLocations(deliveryLocSpinner);
//
//        cutoff_picker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDateTimeDialog(cutoff_picker);
//            }
//        });
//
//        eta_picker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDateTimeDialog(eta_picker);
//            }
//        });
//
//
//        createDeliveryButton.setOnClickListener(new View.OnClickListener() {
//
//            //TODO: Check for empty input
//
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(CreateDeliveryActivity.this, "create button clicked", Toast.LENGTH_SHORT).show();
//
//                String chosenLoc = deliveryLocSpinner.getSelectedItem().toString();  //TODO: Change to array
//                String deliveryFee = deliveryFeeText.getText().toString();
//                String cutoffDateTime = cutoff_picker. getText().toString();
//                String etaDateTime = eta_picker. getText().toString();
//
////                Toast.makeText(v.getContext(), getIntent().getSerializableExtra("user").getClass().getName(),Toast.LENGTH_SHORT).show();
////                Eatery eatery = new Eatery("", "Koi","", "",  ""); // TODO: create real eatery
//                createDeliveryController.recordData(chosenLoc, Double.parseDouble(deliveryFee), cutoffDateTime, etaDateTime, curEatery, v.getContext(), (Deliverer) getIntent().getSerializableExtra("user"));
//
//            }
//        });
////        Toast.makeText(CreateDeliveryActivity.this, "text:" + chosenLoc, Toast.LENGTH_SHORT).show();
//        final BottomNavigationView bot_bar = findViewById(R.id.create_delivery_bottomNavigationView);
//        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
//
//        //TODO: Pass selected location to header
//        createDeliveryController.setLocation(locationDisplay, prevIntent);
//
//    }



// OLD CODE FROM MASTER
// TODO: check if any of the code was updated

//package com.sandbox.chat.ui.activities;
//
//import android.app.DatePickerDialog;
//import android.app.TimePickerDialog;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//import android.text.InputType;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.sandbox.chat.R;
//import com.sandbox.chat.mgr.CreateDeliveryMgr;
//import com.sandbox.chat.mgr.DelivererOfferInteractor;
//import com.sandbox.chat.models.Deliverer;
//import com.sandbox.chat.models.Eatery;
//import com.sandbox.chat.ui.BottomBarOnClickListener;
//import com.sandbox.chat.utils.MultiSpinner;
//
//import java.text.SimpleDateFormat;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.Map;
//
///**
// * For deliverers, display the delivery offer creation interface
// */
//public class CreateDeliveryActivity extends AppCompatActivity {
//
//    Button createDeliveryButton;
//    EditText cutoff_picker;
//    EditText eta_picker;
//    MultiSpinner deliveryLocSpinner;
//    EditText deliveryFeeText;
//    CreateDeliveryMgr createDeliveryController;
//    Button locationDisplay;
//    Intent prevIntent;
//    Eatery curEatery;
//
//
//    /**
//     * Initialize the interface.
//     * Consisting of loading the corresponding layout file and binding the on-click listener to the navigation bar.
//     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_delivery);
//
//        createDeliveryButton = findViewById(R.id.create_delivery_send);
//        cutoff_picker = findViewById(R.id.cutoff_datetime);
//        eta_picker = findViewById(R.id.eta_datetime);
//        deliveryLocSpinner = findViewById(R.id.create_delivery_select_location);
//        deliveryFeeText = findViewById(R.id.create_delivery_fee);
//        createDeliveryController = new CreateDeliveryMgr(this);
//
//        locationDisplay = findViewById(R.id.create_delivery_from_location);
//
//
//    }
//    @Override
//    protected void onStart() {
//
//        super.onStart();
//        prevIntent = getIntent();
//        cutoff_picker.setInputType(InputType.TYPE_NULL);
//        eta_picker.setInputType(InputType.TYPE_NULL);
//        curEatery = ((Eatery)prevIntent.getSerializableExtra("Eatery"));
//        createDeliveryController.setDeliveryLocations(deliveryLocSpinner);
//
//        cutoff_picker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDateTimeDialog(cutoff_picker);
//            }
//        });
//
//        eta_picker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDateTimeDialog(eta_picker);
//            }
//        });
//
//
//        createDeliveryButton.setOnClickListener(new View.OnClickListener() {
//
//            //TODO: Check for empty input
//
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(CreateDeliveryActivity.this, "create button clicked", Toast.LENGTH_SHORT).show();
//
//                String chosenLoc = deliveryLocSpinner.getSelectedItem().toString();  //TODO: Change to array
//                String deliveryFee = deliveryFeeText.getText().toString();
//                String cutoffDateTime = cutoff_picker. getText().toString();
//                String etaDateTime = eta_picker. getText().toString();
//
////                Toast.makeText(v.getContext(), getIntent().getSerializableExtra("user").getClass().getName(),Toast.LENGTH_SHORT).show();
////                Eatery eatery = new Eatery("", "Koi","", "",  ""); // TODO: create real eatery
//                createDeliveryController.recordData(chosenLoc, Double.parseDouble(deliveryFee), cutoffDateTime, etaDateTime, curEatery, v.getContext(), (Deliverer) getIntent().getSerializableExtra("user"));
//
//            }
//        });
////        Toast.makeText(CreateDeliveryActivity.this, "text:" + chosenLoc, Toast.LENGTH_SHORT).show();
//        final BottomNavigationView bot_bar = findViewById(R.id.create_delivery_bottomNavigationView);
//        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
//
//        //TODO: Pass selected location to header
//        createDeliveryController.setLocation(locationDisplay, prevIntent);
//
//    }
//    private void showDateTimeDialog(final EditText date_time_in) {
//        final Calendar calendar=Calendar.getInstance();
//        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                calendar.set(Calendar.YEAR,year);
//                calendar.set(Calendar.MONTH,month);
//                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
//
//                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
//                        calendar.set(Calendar.MINUTE,minute);
//
//                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//
//                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
//                    }
//                };
//
//                new TimePickerDialog(CreateDeliveryActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
//            }
//        };
//
//        new DatePickerDialog(CreateDeliveryActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
//
//    }
//
//}
//
