package com.sandbox.chat.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sandbox.chat.R;
import com.sandbox.chat.core.placeOrder.PlaceOrderContract;
import com.sandbox.chat.core.placeOrder.PlaceOrderPresenter;
import com.sandbox.chat.utils.MultiRadio;

// extends Fragment View.OnClickListener, PlaceOrderContract.View
public class PlaceOrderFragment extends Fragment implements View.OnClickListener, PlaceOrderContract.View{
    private static final String FINAL = PlaceOrderFragment.class.getSimpleName();

//    private Intent i;
    private Button mBtnEateryName, mBtnPlaceOrder;
    private EditText mETxtOrderDetails;
    private MultiRadio mMRadLocationList;

    private PlaceOrderPresenter mPlaceOrderPresenter;
    private ProgressDialog mProgressDialog;

    private String selectedLocation;

    public static PlaceOrderFragment newInstance() {
        Bundle args = new Bundle();
        PlaceOrderFragment fragment = new PlaceOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }
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
    @Override
    public void onStart(){
        super.onStart();
        mBtnPlaceOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mPlaceOrderPresenter.submitOrder(view);
            }
        });
        // buttons -- review where to add

        // presenters

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View fragmentView = inflater.inflate(R.layout.fragment_place_order, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    private void bindViews(View view){
        mBtnEateryName = (Button) view.findViewById(R.id.place_order_eatery_name);
        mETxtOrderDetails = (EditText) view.findViewById(R.id.place_order_order_details);
        mBtnPlaceOrder = (Button) view.findViewById(R.id.place_order_submit);
    }

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

        mPlaceOrderPresenter = new PlaceOrderPresenter(this);

        mBtnEateryName.setOnClickListener(this);
        mBtnPlaceOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.place_order_eatery_name:
                onClickEateryName(view);
                break;
            case R.id.place_order_submit:
                onPlaceOrderSubmit(view);
                break;
        }
    }

    // try to shift this to presenter

    private void onPlaceOrderSubmit(View view) {
    }

    private void onClickEateryName(View view) {
    }

    public Intent getPrevIntent() {
        return getActivity().getIntent();
    }

    public String getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(String selectedLocation) {
        this.selectedLocation = selectedLocation;
    }

}