package com.sandbox.main.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.main.R;
import com.sandbox.main.core.settings.SettingsPresenter;
import com.sandbox.main.ui.BottomBarOnClickListener;
import com.sandbox.main.ui.activities.SettingsActivity;

/**
 * View container for settings activity
 * @see SettingsActivity
 * @author Luong Minh Quang
 */
public class SettingsFragment extends Fragment{

    private Intent prevIntent;
    private SettingsActivity mSettingsActivity;
    private ProgressDialog mProgressDialog;
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

    }

    private void bindViews(View view) {
        final BottomNavigationView bot_bar = view.findViewById(R.id.settings_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

        prevIntent = getActivity().getIntent();

        mapTypeGroup = view.findViewById(R.id.settings_maptype_options);

        mapTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Toast.makeText(getActivity(), "Map choice changed\nClick \"Home\" to view changes", Toast.LENGTH_SHORT).show();
                onChangeMapType(radioGroup.getCheckedRadioButtonId());
            }
        });
//        placeOrderPresenter = new PlaceOrderPresenter(this);
    }


    /**
     * Called when the user set or change the map type, record the changes
     * @param checkedRadioButtonId ID of the radio button selected
     * @see com.sandbox.main.core.settings.SettingsInteractor
     */
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
