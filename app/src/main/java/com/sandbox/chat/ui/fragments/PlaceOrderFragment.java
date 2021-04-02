package com.sandbox.chat.ui.fragments;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.R;
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
    private PlaceOrderPresenter placeOrderPresenter;
    private ProgressDialog mProgressDialog;
    private String selectedLocation;
    private MultiRadio locationList;

    public static PlaceOrderFragment newInstance() {
        Bundle args = new Bundle();
        PlaceOrderFragment fragment = new PlaceOrderFragment();
        fragment.setArguments(args);
        return fragment;
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
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        submitButton = view.findViewById(R.id.place_order_submit);
        locationList = view.findViewById(R.id.spinner2);
        setLocationList(locationList);
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

        String[] locations = placeOrderActivity.getResources().getStringArray(R.array.deliver_to);
        //TODO: Replace string with selected locations by deliverer
        locationList.setItems(locations, "Select location", new MultiRadio.MultiRadioListener() {
            @Override
            public void onItemsSelected(int selected) {
                setSelectedLocation(locationList.getItems(selected));
            }
        });
    }
//    @NonNull View view
    public void onSubmitSelect(Context context) {
        placeOrderPresenter = new PlaceOrderPresenter(placeOrderActivity);
        mProgressDialog.dismiss();
        //TODO: record new order
        Intent intent = new Intent(context,PendingOrdersActivity.class);
//        Intent intent = new Intent(placeOrderActivity.getPrevIntent());
//        intent.setComponent(new ComponentName(view.getContext(), PendingOrdersActivity.class));
        startActivity(intent);
    }

//    public void onSubmitSelect() {
//        Intent intent = new Intent(getContext(), EaterySelectionMapActivity.class);
//        startActivity(intent);
//    }
}
