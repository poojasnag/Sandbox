package com.sandbox.main.core.maps;

import android.content.Context;

import com.google.android.libraries.maps.GoogleMap;

import java.io.IOException;

/**
 * Contract interface for MapsActivity
 * @author Pooja Srinivas Nag
 * @author Mun Kei Wuai
 * @author Tan Wen Xiu
 */
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
