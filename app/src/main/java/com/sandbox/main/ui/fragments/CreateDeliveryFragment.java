package com.sandbox.main.ui.fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.sandbox.main.R;
import com.sandbox.main.core.createDelivery.CreateDeliveryContract;
import com.sandbox.main.core.createDelivery.CreateDeliveryPresenter;
import com.sandbox.main.interactors.DelivererOfferInteractor;
import com.sandbox.main.models.Buyer;
import com.sandbox.main.models.Deliverer;
import com.sandbox.main.models.DelivererOffer;
import com.sandbox.main.models.Eatery;
import com.sandbox.main.models.Transaction;
import com.sandbox.main.models.User;
import com.sandbox.main.ui.activities.CreateDeliveryActivity;
import com.sandbox.main.ui.activities.PendingOrdersActivity;
import com.sandbox.main.utils.MultiSpinner;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

// TODO: check against activity code from previous master for the selected orders part

/**
 * View holder for CreateDeliveryActivity
 * @author Chua Zi Heng
 * @author  Luong Minh Quang
 */
public class CreateDeliveryFragment extends Fragment implements View.OnClickListener, CreateDeliveryContract.View {
    private static final String TAG = CreateDeliveryFragment.class.getSimpleName();

    private Intent i;
    private CreateDeliveryPresenter createDeliveryPresenter;
    private CreateDeliveryActivity createDeliveryActivity;
    private Intent prevIntent;
    private ProgressDialog mProgressDialog;

    private EditText cutoff_picker, eta_picker, deliveryFeeText;
    private Button createDeliveryButton, locationDisplay;
    private MultiSpinner deliveryLocSpinner;
    private Eatery curEatery;
    ArrayList<String> selectedLocations;

    // done
    public static CreateDeliveryFragment newInstance() {
        CreateDeliveryFragment fragment = new CreateDeliveryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    // done
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_create_delivery, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    // done
    private void bindViews(View view){
        createDeliveryButton = (Button) view.findViewById(R.id.create_delivery_send);
        locationDisplay = (Button) view.findViewById(R.id.create_delivery_from_location);
        cutoff_picker = (EditText) view.findViewById(R.id.cutoff_datetime);
        eta_picker = (EditText) view.findViewById(R.id.eta_datetime);
        deliveryFeeText = (EditText) view.findViewById(R.id.create_delivery_fee);
        deliveryLocSpinner = (MultiSpinner) view.findViewById(R.id.create_delivery_select_location);
        createDeliveryActivity = (CreateDeliveryActivity) getActivity();
        i = createDeliveryActivity.getIntent();
    }

    // done
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        init();
    }


    private void init(){
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle("Loading");
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

        createDeliveryPresenter = new CreateDeliveryPresenter(this);
        // get message from presenter??

        createDeliveryButton.setOnClickListener(this);
        // locationDisplay.setOnClickListener(this);
        eta_picker.setOnClickListener(this);
        cutoff_picker.setOnClickListener(this);
        prevIntent = getActivity().getIntent();
        cutoff_picker.setInputType(InputType.TYPE_NULL);
        eta_picker.setInputType(InputType.TYPE_NULL);
        curEatery = ((Eatery)prevIntent.getSerializableExtra("Eatery"));
        createDeliveryPresenter.onSetDeliveryLocations(deliveryLocSpinner);

        cutoff_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(cutoff_picker, v);
            }
        });

        eta_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(eta_picker, v);
            }
        });


        createDeliveryButton.setOnClickListener(new View.OnClickListener() {

            //TODO: Check for empty input

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                onCreateDeliveryOffer(v);
            }
        });
//        Toast.makeText(CreateDeliveryActivity.this, "text:" + chosenLoc, Toast.LENGTH_SHORT).show();

        //TODO: Pass selected location to header
        createDeliveryPresenter.onSetLocation(locationDisplay, prevIntent);
    }

    /**
     * Called when the user click on "Create" button
     * Validates the inputs, then records the data to the database
     * Shows an error message if the inputs are invalid
     * @param v The "Create" button
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onCreateDeliveryOffer(View v) {

        String deliveryFee = deliveryFeeText.getText().toString();
        String cutoffDateTime = cutoff_picker. getText().toString();
        String etaDateTime = eta_picker. getText().toString();
        Log.e("insideactivity", selectedLocations.toString());
        if(createDeliveryPresenter.validateCreateDelivery(deliveryFee,etaDateTime, cutoffDateTime,selectedLocations))
        {
            createDeliveryPresenter.onRecordData(selectedLocations, Double.parseDouble(deliveryFee), cutoffDateTime, etaDateTime, curEatery, v.getContext(), (Deliverer) getActivity().getIntent().getSerializableExtra("user"));
            Toast.makeText(getActivity(), "Delivery offer submitted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(i);
            intent.setComponent(new ComponentName(getContext(),PendingOrdersActivity.class));
            startActivity(intent);

        }
        else if (createDeliveryPresenter.invalidateCreateDelivery(deliveryFee,etaDateTime, cutoffDateTime,selectedLocations)!=null){
            String message = createDeliveryPresenter.invalidateCreateDelivery(deliveryFee,etaDateTime, cutoffDateTime,selectedLocations);
            Toast.makeText(createDeliveryActivity,message, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(createDeliveryActivity,"Invalid inputs", Toast.LENGTH_SHORT).show();
        }

    }


    @RequiresApi(api=Build.VERSION_CODES.O)
    @Override
    public void onClick(View view){
        int viewId = view.getId();

        switch (viewId) {
            case R.id.create_delivery_send:
                onSubmitDelivery(view.getContext());
                break;
        }
    }


    private void onSubmitDelivery(Context context) {


        createDeliveryPresenter = new CreateDeliveryPresenter(createDeliveryActivity);

        mProgressDialog.dismiss();
        Intent intent = new Intent(i);

        intent.setComponent(new ComponentName(context, PendingOrdersActivity.class));
//        Intent intent = new Intent(context, PendingOrdersActivity.class);
        getActivity().startActivity(intent);
    }




//    @Override
//    public void onStart() {
//
//        super.onStart();
//        prevIntent = getActivity().getIntent();
//        cutoff_picker.setInputType(InputType.TYPE_NULL);
//        eta_picker.setInputType(InputType.TYPE_NULL);
//        curEatery = ((Eatery)prevIntent.getSerializableExtra("Eatery"));
//        createDeliveryPresenter.onSetDeliveryLocations(deliveryLocSpinner);
//
//        cutoff_picker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDateTimeDialog(cutoff_picker, v);
//            }
//        });
//
//        eta_picker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDateTimeDialog(eta_picker, v);
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
//                Toast.makeText(getActivity(), "create button clicked", Toast.LENGTH_SHORT).show();
//
////                String chosenLoc = deliveryLocSpinner.getSelectedItem().toString();  //TODO: Change to array
//                String deliveryFee = deliveryFeeText.getText().toString();
//                String cutoffDateTime = cutoff_picker. getText().toString();
//                String etaDateTime = eta_picker. getText().toString();
//                Log.e("insideactivity", selectedLocations.toString());
//
////                Toast.makeText(v.getContext(), getIntent().getSerializableExtra("user").getClass().getName(),Toast.LENGTH_SHORT).show();
////                Eatery eatery = new Eatery("", "Koi","", "",  ""); // TODO: create real eatery
//                createDeliveryPresenter.onRecordData(selectedLocations, Double.parseDouble(deliveryFee), cutoffDateTime, etaDateTime, curEatery, v.getContext(), (Deliverer) getActivity().getIntent().getSerializableExtra("user"));
//
//            }
//        });
////        Toast.makeText(CreateDeliveryActivity.this, "text:" + chosenLoc, Toast.LENGTH_SHORT).show();
//
//        //TODO: Pass selected location to header
//        createDeliveryPresenter.onSetLocation(locationDisplay, prevIntent);
//
//    }

    private void showDateTimeDialog(final EditText date_time_in, View v) {
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

                new TimePickerDialog(v.getContext(),timeSetListener,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(v.getContext(),dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    public Buyer createBuyer(User user)
    {
        //TODO: Call TransactionInteractor to get all the transactions
        return new Buyer(user.getUid(), user.getEmail(), user.getFirebaseToken(), user.getRating() , user.getRatingCount(), new LinkedList<Transaction>());
    }

    public Deliverer createDeliverer(User user)
    {
        //TODO: Call TransactionInteractor to get all the transactions
        return new Deliverer(user.getUid(), user.getEmail(), user.getFirebaseToken(), user.getRating(), user.getRatingCount(),new LinkedList<Transaction>());
    }

    /**
     * Records a new transaction to the database from the user's inputs
     * @param locationsList A list of delivery destinations selected
     * @param deliveryFee The delivery fee
     * @param cutoffDateTime The cut-off time
     * @param etaDateTime The estimated time of arrival
     * @param eatery The selected eatery
     * @param context An instance of CreateDeliveryActivity
     * @param deliverer The current user
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void recordData(ArrayList<String> locationsList, double deliveryFee, String cutoffDateTime, String etaDateTime, Eatery eatery, Context context, Deliverer deliverer) {
        long unixTime = Instant.now().getEpochSecond();
        String curTime = Long.toString(unixTime);

        if (deliverer != null) {
            String email = deliverer.getEmail();
            String offerID = curTime + "-" + email;  //KEY: OfferID which is current time + uid (unique in every scenario)
            DelivererOffer delivererOffer = new DelivererOffer(offerID,cutoffDateTime, etaDateTime, deliveryFee, locationsList, eatery, deliverer, curTime);
//            Toast.makeText(context, delivererOffer.getClass().getName(), Toast.LENGTH_SHORT).show();
            DelivererOfferInteractor.setData(delivererOffer, context);
        }
    }


    @Override
    public void setLocation(Button b, Intent i) {
        Eatery e = (Eatery) i.getSerializableExtra("Eatery");
        b.setText(e.getEateryName());
    }

    /**
     * Initializes the list of possible delivery destinations
     * @param deliveryLocSpinner The view containing the list
     */
    @Override
    public void setDeliveryLocations(MultiSpinner deliveryLocSpinner) {

        selectedLocations = new ArrayList<String>();
        deliveryLocSpinner.setItems(getActivity().getResources().getStringArray(R.array.deliver_to) ,"Select locations" ,new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                selectedLocations = deliveryLocSpinner.getAllSelected(selected);
            }
        });

    }


    //    // dk whether need anot
//    public void onStart(){
//        super.onStart();
//        i = getActivity().getIntent();
//        // other stuff
//        // call presenter functions
//    }

}