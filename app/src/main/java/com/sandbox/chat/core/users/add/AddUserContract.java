package com.sandbox.chat.core.users.add;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;


/**
 * An interface for adding of users that specifies the contract between view, presenter, interactor and listener
 *
 * @author chua zi heng
 */
public interface AddUserContract {
    interface View {
        void onAddUserSuccess(String message);

        void onAddUserFailure(String message);
    }

    interface Presenter {
        void addUser(Context context, FirebaseUser firebaseUser);
    }

    interface Interactor {
        void addUserToDatabase(Context context, FirebaseUser firebaseUser);
    }

    interface OnUserDatabaseListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}
