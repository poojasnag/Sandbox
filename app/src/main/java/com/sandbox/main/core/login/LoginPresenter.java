package com.sandbox.main.core.login;

import android.app.Activity;

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
//        UserInteractor.getUserDocument(fUser.getUid())
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
//        UserInteractor.setData(curUser, context);
//        return curUser;
//
//    }

}
