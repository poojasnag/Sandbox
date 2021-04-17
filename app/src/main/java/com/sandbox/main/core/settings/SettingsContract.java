package com.sandbox.main.core.settings;

public interface SettingsContract {
    interface Interactor{
        static int getMapType() {
            return 0;
        }

        static void setMapType(int type) {

        }
    }
    interface Presenter {
        void setMapType(int type);
    }
    interface View
    {
        void onChangeMapType(int checkedButtonID);
    }

}
