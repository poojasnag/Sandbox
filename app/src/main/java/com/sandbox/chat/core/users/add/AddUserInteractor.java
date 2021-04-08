package com.sandbox.chat.core.users.add;

import android.content.Context;
import androidx.annotation.NonNull;

import com.sandbox.chat.R;
import com.sandbox.chat.models.User;
import com.sandbox.chat.utils.Constants;
import com.sandbox.chat.utils.SharedPrefUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Sending new user data to the Firebase real-time database
 *
 * @author chua zi heng
 */

public class AddUserInteractor implements AddUserContract.Interactor {
    private AddUserContract.OnUserDatabaseListener mOnUserDatabaseListener;

    public AddUserInteractor(AddUserContract.OnUserDatabaseListener onUserDatabaseListener) {
        this.mOnUserDatabaseListener = onUserDatabaseListener;
    }

    @Override
    public void addUserToDatabase(final Context context, FirebaseUser firebaseUser) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        ArrayList<Integer> ratingArray = new ArrayList<Integer>();
        ratingArray.add(2);
        User user = new User(firebaseUser.getUid(),
                firebaseUser.getEmail(),
                new SharedPrefUtil(context).getString(Constants.ARG_FIREBASE_TOKEN),ratingArray);
        database.child(Constants.ARG_USERS)
                .child(firebaseUser.getUid())
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mOnUserDatabaseListener.onSuccess(context.getString(R.string.user_successfully_added));
                        } else {
                            mOnUserDatabaseListener.onFailure(context.getString(R.string.user_unable_to_add));
                        }
                    }
                });
    }
}
