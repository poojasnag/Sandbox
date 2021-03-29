package com.sandbox.chat.mgr;

/**
 * @deprecated
 */
public interface BnDContract {
    interface View{ //BnDActivity
        // ...
        // EaterySelectionMapActivity
    }
    interface Presenter{  //BnDPresenter
        void onBuyerSelect();
        void onDelivererSelect();
    }
}
