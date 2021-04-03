package com.sandbox.chat.core.logout;
/**
 * An interface for user logout that specifies the contract between view, presenter, interactor and listener
 *
 * @author chua zi heng
 */
public interface    LogoutContract {
    interface View {
        void onLogoutSuccess(String message);

        void onLogoutFailure(String message);
    }

    interface Presenter {
        void logout();
    }

    interface Interactor {
        void performFirebaseLogout();
    }

    interface OnLogoutListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}
