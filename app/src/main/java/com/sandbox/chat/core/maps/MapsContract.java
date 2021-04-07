package com.sandbox.chat.core.maps;

import com.google.android.libraries.maps.GoogleMap;

import android.content.Context;

import java.io.IOException;

public interface MapsContract {

    interface View {
        void onMapReady(GoogleMap googleMap);
    }

    interface Presenter{
//        void onStart();
//
//        void getCurrentLocation();


    }

    interface Interactor{
        static void initialize(Context context) throws IOException {}
    }

}
