package com.sandbox.main.core.registration;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

/**
 * An interface for user registration that specifies the contract between view, presenter, interactor and listener
 *
 * @author chua zi heng
 */
public interface RegisterContract {
    interface View {
        void onRegistrationSuccess(FirebaseUser firebaseUser);

        void onRegistrationFailure(String message);
    }

    interface Presenter {
        void register(Activity activity, String email, String password);
    }

    interface Interactor {
        void performFirebaseRegistration(Activity activity, String email, String password);
    }

    interface OnRegistrationListener {
        void onSuccess(FirebaseUser firebaseUser);

        void onFailure(String message);
    }
}
