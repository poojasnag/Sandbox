package com.sandbox.main.core.createDelivery;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import com.sandbox.main.models.Buyer;
import com.sandbox.main.models.Deliverer;
import com.sandbox.main.models.Eatery;
import com.sandbox.main.models.User;
import com.sandbox.main.utils.MultiSpinner;

import java.util.ArrayList;

/**
 * Contract interface for CreateDeliveryActivity
 * @author Pooja Srinivas Nag
 * @author Mun Kei Wuai
 * @author Tan Wen Xiu
 */
public interface CreateDeliveryContract {
    interface View{
        // add functions that were called within the presenter
            Buyer createBuyer(User user);
            Deliverer createDeliverer(User user);
            void recordData(ArrayList<String> chosenLoc, double deliveryFee, String cutoffDateTime,
                            String etaDateTime, Eatery eatery, Context context, Deliverer deliverer);
            void setLocation(Button b, Intent i);
            void setDeliveryLocations(MultiSpinner deliveryLocSpinner);
    }
    interface Presenter{
        // add the functions where the presenter calls the interactor
        void onCreateBuyer(User user);
        void onCreateDeliverer(User user);
        void onRecordData(ArrayList<String> chosenLoc, double deliveryFee, String cutoffDateTime,
                          String etaDateTime, Eatery eatery, Context context, Deliverer deliverer);
        void onSetLocation(Button b, Intent i);
        void onSetDeliveryLocations(MultiSpinner deliveryLocSpinner);
        boolean validateCreateDelivery(String deliveryFee, String etaDateTime, String cutoffDateTime, ArrayList<String> selectedLocations);
    }

    interface Interactor{
        // add the interactor function (function that calls the database)
    }

    interface OnCreateDeliveryListener{
        // add functions  where presenter calls view
    }

}
