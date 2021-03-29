package com.sandbox.chat.core.login;

import android.app.Activity;
/**
 * An interface for user login that specifies the contract between view, presenter, interactor and listener
 *
 * @author chua zi heng
 */
public interface LoginContract {
    interface View {
        void onLoginSuccess(String message);

        void onLoginFailure(String message);
    }

    interface Presenter {
        void login(Activity activity, String email, String password);
    }

    interface Interactor {
        void performFirebaseLogin(Activity activity, String email, String password);
    }

    interface OnLoginListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}