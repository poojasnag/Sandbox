package com.sandbox.chat.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.R;
import com.sandbox.chat.core.settings.SettingsPresenter;
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.ui.activities.SettingsActivity;

public class SettingsFragment extends Fragment implements View.OnClickListener{

    private Intent prevIntent;
    private SettingsActivity mSettingsActivity;
    private ProgressDialog mProgressDialog;
    private Button logoutButton;
    private RadioGroup mapTypeGroup;
    private SettingsPresenter mSettingsPresenter;

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_settings, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        mSettingsPresenter = new SettingsPresenter((SettingsActivity) getActivity());
        mSettingsActivity = (SettingsActivity) getActivity();
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

        logoutButton.setOnClickListener(this);
    }

    private void bindViews(View view) {
        final BottomNavigationView bot_bar = view.findViewById(R.id.settings_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

        prevIntent = getActivity().getIntent();
        logoutButton = view.findViewById(R.id.settings_logout);
        mapTypeGroup = view.findViewById(R.id.settings_maptype_options);

        mapTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.e("changeRadio", "Choice changed");
                onChangeMapType(radioGroup.getCheckedRadioButtonId());
            }
        });
//        placeOrderPresenter = new PlaceOrderPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {

        }
    }

    public void onChangeMapType(int checkedRadioButtonId)
    {
        switch (checkedRadioButtonId) {
            case R.id.maptype_1:
                mSettingsPresenter.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.maptype_2:
                mSettingsPresenter.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.maptype_3:
                mSettingsPresenter.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;


        }
    }
}
