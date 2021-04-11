package com.sandbox.chat.core.chooseDeliverer;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.Eatery;


// delete the Interactor for this package
public interface ChooseDelivererContract {
    interface View{
//        interface/fragment functions
//        void onRegistrationSuccess(FirebaseUser firebaseUser);
//        void onRegistrationFailure(String message);
    }
    interface Presenter {
        void getDeliverers(RecyclerView ordersList, Eatery eatery, Intent i);
        void setLocation(Button b, Intent i);
    }
    interface Interactor {
        // interactor function (firebase)
    }

//    interface OnChooseDelivererListener{
//        // functions from presenter - what next?
//    }
}
