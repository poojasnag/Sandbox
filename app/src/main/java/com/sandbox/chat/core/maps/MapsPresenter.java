package com.sandbox.chat.core.maps;

//import com.sandbox.chat.mgr.EateryMgr;
//import com.sandbox.chat.ui.activities.EaterySelectionMapActivity;
import com.google.android.libraries.maps.GoogleMap;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.ui.activities.MapsActivity;

import java.io.IOException;
import java.util.ArrayList;

public class MapsPresenter implements MapsContract.Presenter, MapsContract.View{

    //private MapsInteractor mMapsInteractor;
    private final MapsActivity mMapsActivity;
    private MapsContract.View mView;
    private MapsInteractor eateryData;

    public MapsPresenter(MapsActivity mMapsActivity) throws IOException, ClassNotFoundException {
        this.mMapsActivity = mMapsActivity;
    }

    public MapsPresenter(MapsActivity mMapsActivity, ArrayList<Eatery> eateries) throws IOException, ClassNotFoundException {
        this.mMapsActivity = mMapsActivity;
        this.eateryData = new MapsInteractor(eateries);
    }

    public void onMapReady(GoogleMap googleMap){

    };


}
