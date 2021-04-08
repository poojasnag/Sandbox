package com.sandbox.chat.core.createDelivery;

public class CreateDeliveryInteractor implements CreateDeliveryContract.Interactor{
    private static final String TAG = CreateDeliveryInteractor.class.getSimpleName();

    private CreateDeliveryContract.OnCreateDeliveryListener mOnCreateDeliveryListener;

    public CreateDeliveryInteractor(CreateDeliveryContract.OnCreateDeliveryListener onCreateDeliveryListener){
        this.mOnCreateDeliveryListener = onCreateDeliveryListener;
    }

    // insert function that calls the database
}
