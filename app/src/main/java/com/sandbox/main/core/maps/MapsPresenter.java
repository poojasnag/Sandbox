package com.sandbox.main.core.maps;

//import com.sandbox.chat.mgr.EateryMgr;
//import com.sandbox.chat.ui.activities.EaterySelectionMapActivity;

import com.google.android.libraries.maps.GoogleMap;
import com.sandbox.main.models.Eatery;
import com.sandbox.main.ui.activities.MapsActivity;

import java.util.ArrayList;

/**
 * Presenter class for MapsActivity
 * @author Pooja Srinivas Nag
 * @author Mun Kei Wuai
 * @author Tan Wen Xiu
 */
public class MapsPresenter implements MapsContract.Presenter, MapsContract.View{

    //private MapsInteractor mMapsInteractor;
    private final MapsActivity mMapsActivity;
    private MapsContract.View mView;
    private MapsInteractor eateryData;

    public MapsPresenter(MapsActivity mMapsActivity)  {
        this.mMapsActivity = mMapsActivity;
    }

    public MapsPresenter(MapsActivity mMapsActivity, ArrayList<Eatery> eateries)  {
        this.mMapsActivity = mMapsActivity;
        this.eateryData = new MapsInteractor(eateries);
    }

    public void onMapReady(GoogleMap googleMap){

    };


}
