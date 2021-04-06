package com.sandbox.chat.core.createDelivery;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.models.User;
import com.sandbox.chat.utils.MultiSpinner;

public interface CreateDeliveryContract {
    interface View{
        // add functions that were called within the presenter
            Buyer createBuyer(User user);
            Deliverer createDeliverer(User user);
            void recordData(String chosenLoc, double deliveryFee, String cutoffDateTime,
                            String etaDateTime, Eatery eatery, Context context, Deliverer deliverer);
            void setLocation(Button b, Intent i);
            void setDeliveryLocations(MultiSpinner deliveryLocSpinner);
    }
    interface Presenter{
        // add the functions where the presenter calls the interactor
        void onCreateBuyer(User user);
        void onCreateDeliverer(User user);
        void onRecordData(String chosenLoc, double deliveryFee, String cutoffDateTime, 
                        String etaDateTime, Eatery eatery, Context context, Deliverer deliverer);
        void onSetLocation(Button b, Intent i);
        void onSetDeliveryLocations(MultiSpinner deliveryLocSpinner);
    }

    interface Interactor{
        // add the interactor function (function that calls the database)
    }

    interface OnCreateDeliveryListener{
        // add functions  where presenter calls view
    }

}
