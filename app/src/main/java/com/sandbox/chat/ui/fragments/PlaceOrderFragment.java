package com.sandbox.chat.ui.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.R;
import com.sandbox.chat.mgr.TransactionMgr;
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.models.Status;
import com.sandbox.chat.models.Transaction;
import com.sandbox.chat.models.User;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.ui.activities.PendingOrdersActivity;
import com.sandbox.chat.ui.activities.PlaceOrderActivity;
import com.sandbox.chat.ui.contract.BnDContract;
import com.sandbox.chat.ui.contract.PlaceOrderContract;
import com.sandbox.chat.ui.presenter.BnDPresenter;
import com.sandbox.chat.ui.presenter.PlaceOrderPresenter;
import com.sandbox.chat.utils.MultiRadio;

import java.time.Instant;

/**
 * View container for PlaceOrderActivity
 */
public class PlaceOrderFragment extends Fragment implements View.OnClickListener, PlaceOrderContract.View{
    private PlaceOrderActivity placeOrderActivity;
    private Button submitButton;
    private Button eateryName;
    private PlaceOrderPresenter placeOrderPresenter;
    private ProgressDialog mProgressDialog;
    private String selectedLocation;
    private MultiRadio locationList;
    private EditText orderDetails;
    private Intent i;

    private DelivererOffer curDelivererOffer;
    private TextView delivererName;
    private TextView eta;
    private TextView deliveryRate;


    public static PlaceOrderFragment newInstance() {
        Bundle args = new Bundle();
        PlaceOrderFragment fragment = new PlaceOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onStart() {
        super.onStart();
        submitButton.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //TODO: I temporarily disconnected this from PlaceOrderPresenter
                PlaceOrderFragment.this.onSubmitSelect(placeOrderActivity);

            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_place_order, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    private void bindViews(View view) {
        final BottomNavigationView bot_bar = view.findViewById(R.id.place_order_bottomNavigationView);

        i = getActivity().getIntent();
        curDelivererOffer = (DelivererOffer) i.getSerializableExtra("delivererOffer");

        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        submitButton = view.findViewById(R.id.place_order_submit);
        locationList = view.findViewById(R.id.spinner2);
        eateryName = view.findViewById(R.id.place_order_eatery_name);
        orderDetails = view.findViewById(R.id.place_order_order);
        delivererName = view.findViewById(R.id.place_order_deliverer_name);
        deliveryRate = view.findViewById(R.id.place_order_rate);
        eta = view.findViewById(R.id.place_order_eta);
        setLocationList(locationList);
        setEateryName(eateryName);
        setDetails(delivererName, deliveryRate, eta, curDelivererOffer);

//        placeOrderPresenter = new PlaceOrderPresenter(this);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        placeOrderPresenter = new PlaceOrderPresenter((PlaceOrderActivity) getActivity());
        placeOrderActivity = (PlaceOrderActivity) getActivity();
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);
        submitButton.setOnClickListener(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        switch (viewId) {
            case R.id.place_order_submit:
                onSubmitSelect(getActivity());
                break;
        }
    }

    public void setSelectedLocation(String selectedLocation) {
        this.selectedLocation = selectedLocation;
    }

    /**
     * Initialize possible delivery destinations
     * @param locationList The radio buttons group used to display the locations
     */
    public void setLocationList(MultiRadio locationList) {
        String[] locations =  curDelivererOffer.getDeliveryLocation().toArray(new String[0]);
        locationList.setItems(locations, "Select location", new MultiRadio.MultiRadioListener() {
            @Override
            public void onItemsSelected(int selected) {
                setSelectedLocation(locationList.getItems(selected));
            }
        });
    }

    /**
     * Set the content for text boxes inside the fragment
     * @param delivererName TextView showing the deliverer's name
     * @param deliveryRate TextView showing the delivery fee
     * @param eta  TextView showing estimated time of arrival
     * @param d The delivery offer that the buyer selected from the previous activity
     */
    public void setDetails(TextView delivererName, TextView deliveryRate, TextView eta, DelivererOffer d)
    {
        delivererName.setText("Deliverer: " + d.getDeliverer().getEmail());
        deliveryRate.setText("Rate: $" + d.getDeliveryFee());
        eta.setText("Estimated time of arrival: "+ d.getEtaTime());
    }

    public void setEateryName(Button b)
    {
        b.setText(((Eatery)(i.getSerializableExtra("Eatery"))).getEateryName());
    }

    /**
     * Called when the user clicks on the submit button.
     * Records the order to the database, and navigates the user to PendingOrdersActivity, where they can see the order that they created
     * @param context
     */
//    @NonNull View view
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onSubmitSelect(Context context) {
        placeOrderPresenter = new PlaceOrderPresenter(placeOrderActivity);

        mProgressDialog.dismiss();
        String buyerID = ((User) i.getSerializableExtra("user")).getUid();
        String buyerName = ((User) i.getSerializableExtra("user")).getEmail();
        String delivererName = curDelivererOffer.getDeliverer().getEmail();
        String delivererOfferID = curDelivererOffer.getDelivererOfferID();
        String delivererID = curDelivererOffer.getDeliverer().getUid();
        String eateryName = curDelivererOffer.getEatery().getEateryName();
        String buyerLocation = selectedLocation;
        String order = orderDetails.getText().toString();
        Status orderStatus = Status.PENDING;
        Status delivererStatus = Status.PENDING;
        Status buyerStatus = Status.PENDING;

        if(buyerLocation == null || buyerLocation == locationList.getDefaultText())
        {
            Toast.makeText(context, "Invalid order!\nA delivery location must be selected", Toast.LENGTH_SHORT).show();
        }
        else if(order.trim().equals(""))
        {
            Toast.makeText(context, "Invalid order!\nOrder details is empty", Toast.LENGTH_SHORT).show();
        }
        else if(order.length() > 5000)
        {
            Toast.makeText(context, "Invalid order!\nOrder is too long", Toast.LENGTH_SHORT).show();
        }

        else {
            long unixTime = Instant.now().getEpochSecond();
            String curTime = Long.toString(unixTime);

            String transactionID = buyerID + '-' + delivererOfferID + '-' + curTime;
            Transaction t = new Transaction(eateryName, transactionID, buyerName, delivererName, buyerID, delivererOfferID, delivererID, buyerLocation, order, orderStatus, delivererStatus, buyerStatus);
            TransactionMgr.setData(t, context);

            Intent intent = new Intent(i);
            intent.setComponent(new ComponentName(context, PendingOrdersActivity.class));

            Toast.makeText(context, "Order successfully placed", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

//    public void onSubmitSelect() {
//        Intent intent = new Intent(getContext(), EaterySelectionMapActivity.class);
//        startActivity(intent);
//    }
}
