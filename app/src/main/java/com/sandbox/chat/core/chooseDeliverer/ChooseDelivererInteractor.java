package com.sandbox.chat.core.chooseDeliverer;

public class ChooseDelivererInteractor implements ChooseDelivererContract.Interactor{
    private static final String TAG = ChooseDelivererInteractor.class.getSimpleName();

    private ChooseDelivererContract.OnChooseDelivererListener mOnChooseDelivererListener;

    public ChooseDelivererInteractor(ChooseDelivererContract.OnChooseDelivererListener onChooseDelivererListener) {
        this.mOnChooseDelivererListener = onChooseDelivererListener;
    }

//    @Override
    // the get data method
    // public void ....

}
