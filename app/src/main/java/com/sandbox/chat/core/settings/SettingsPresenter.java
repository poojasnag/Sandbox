package com.sandbox.chat.core.settings;

import android.provider.Settings;

import com.sandbox.chat.ui.activities.SettingsActivity;

public class SettingsPresenter {
    private SettingsActivity mSettingsActivity;
    public SettingsPresenter(SettingsActivity activity) {
        mSettingsActivity = activity;
    }
    public void setMapType(int type)
    {
        SettingsInteractor.setMapType(type);
    }

}
