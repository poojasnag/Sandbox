package com.sandbox.chat.ui.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.sandbox.chat.ui.activities.EaterySelectionMapActivity;
import com.sandbox.chat.ui.activities.PendingOrdersActivity;
import com.sandbox.chat.ui.activities.PlaceOrderActivity;
import com.sandbox.chat.ui.contract.BnDContract;
import com.sandbox.chat.ui.contract.PlaceOrderContract;
import com.sandbox.chat.ui.presenter.BnDPresenter;
import com.sandbox.chat.ui.presenter.PlaceOrderPresenter;
import com.sandbox.chat.utils.MultiRadio;

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


    public static PlaceOrderFragment newInstance() {
        Bundle args = new Bundle();
        PlaceOrderFragment fragment = new PlaceOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onStart() {
        super.onStart();

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                placeOrderPresenter.onSubmitSuccess();
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

        setLocationList(locationList);
        setEateryName(eateryName);

//        placeOrderPresenter = new PlaceOrderPresenter(this);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        placeOrderPresenter = new PlaceOrderPresenter((com.sandbox.chat.ui.contract.PlaceOrderContract.View) this);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

        submitButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        switch (viewId) {
            case R.id.place_order_submit:
                onSubmitSelect(getContext());
                break;
        }
    }

    public void setSelectedLocation(String selectedLocation) {
        this.selectedLocation = selectedLocation;
    }

    public void setLocationList(MultiRadio locationList) {


        String[] locations =  curDelivererOffer.getDeliveryLocation().toArray(new String[0]);
        locationList.setItems(locations, "Select location", new MultiRadio.MultiRadioListener() {
            @Override
            public void onItemsSelected(int selected) {
                setSelectedLocation(locationList.getItems(selected));
            }
        });
    }

    public void setEateryName(Button b)
    {
        b.setText(((Eatery)(i.getSerializableExtra("Eatery"))).getEateryName());
    }

//    @NonNull View view
    public void onSubmitSelect(Context context) {
        placeOrderPresenter = new PlaceOrderPresenter(placeOrderActivity);
        placeOrderActivity = new PlaceOrderActivity();
        mProgressDialog.dismiss();


        String buyerID = ((User)i.getSerializableExtra("user")).getUid();
        String delivererOfferID = curDelivererOffer.getDelivererOfferID();
        String delivererID = curDelivererOffer.getDeliverer().getUid();
        String buyerLocation = selectedLocation;
        String order = orderDetails.getText().toString();
        Status orderStatus = Status.PENDING;
        Status delivererStatus = Status.PENDING;
        Status buyerStatus = Status.PENDING;

        //TODO: Zi Heng, here's the transaction object from placeOrder.
        Transaction t = new Transaction(buyerID, delivererOfferID, delivererID, buyerLocation, order, orderStatus, delivererStatus, buyerStatus);
        TransactionMgr.setData(t, context);

        Intent intent = new Intent(i);
        intent.setComponent(new ComponentName(context,PendingOrdersActivity.class));

        startActivity(intent);
    }

//    public void onSubmitSelect() {
//        Intent intent = new Intent(getContext(), EaterySelectionMapActivity.class);
//        startActivity(intent);
//    }
}
