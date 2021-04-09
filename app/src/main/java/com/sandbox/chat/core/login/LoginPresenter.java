package com.sandbox.chat.core.login;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.sandbox.chat.mgr.UserMgr;
import com.sandbox.chat.models.User;
import com.sandbox.chat.utils.Constants;
import com.sandbox.chat.utils.SharedPrefUtil;

import java.util.ArrayList;

/**
 * Presentation logic for user login
 *
 * @author chua zi heng
 */
public class LoginPresenter implements LoginContract.Presenter, LoginContract.OnLoginListener {
    private LoginContract.View mLoginView;
    private LoginInteractor mLoginInteractor;

    public LoginPresenter(LoginContract.View loginView) {
        this.mLoginView = loginView;
        mLoginInteractor = new LoginInteractor(this);
    }

    @Override
    public void login(Activity activity, String email, String password) {
        mLoginInteractor.performFirebaseLogin(activity, email, password);
    }

    @Override
    public void onSuccess(String message) {
        mLoginView.onLoginSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        mLoginView.onLoginFailure(message);
    }

//    public static User findUser(Context context)
//    {
//
//        //TODO:Get the user
//        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
//        //TODO testing rating
//        UserMgr.getUserDocument(fUser.getUid())
//                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    rating = document.get("rating");
//                }
//            }
//        });
//        User curUser =  new User(fUser.getUid() ,fUser.getEmail(), new SharedPrefUtil(context).getString(Constants.ARG_FIREBASE_TOKEN), 0, );
//
//        UserMgr.setData(curUser, context);
//        return curUser;
//
//    }

}
