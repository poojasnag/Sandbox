package com.sandbox.main.core.logout;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Sending logout message to the Firebase authentication
 *
 * @author chua zi heng
 */

public class LogoutInteractor implements LogoutContract.Interactor {
    private LogoutContract.OnLogoutListener mOnLogoutListener;

    public LogoutInteractor(LogoutContract.OnLogoutListener onLogoutListener) {
        mOnLogoutListener = onLogoutListener;
    }

    /**
     * Logs the user out of the application
     */
    @Override
    public void performFirebaseLogout() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
            mOnLogoutListener.onSuccess("Successfully logged out!");
        } else {
            mOnLogoutListener.onFailure("No user logged in yet!");
        }
    }
}
