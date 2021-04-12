package com.sandbox.main.core.settings;

import com.sandbox.main.ui.activities.SettingsActivity;
/**
 * Presenter class for SettingsActivity
 * @author Luong Minh Quang
 */
public class SettingsPresenter implements SettingsContract.Presenter {
    private SettingsActivity mSettingsActivity;
    public SettingsPresenter(SettingsActivity activity) {
        mSettingsActivity = activity;
    }
    @Override
    public void setMapType(int type)
    {
        SettingsInteractor.setMapType(type);
    }

}
