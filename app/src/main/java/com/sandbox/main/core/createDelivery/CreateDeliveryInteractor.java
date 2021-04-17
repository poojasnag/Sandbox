package com.sandbox.main.core.createDelivery;

/**
 * Interactor class for CreateDeliveryActivity
 * @author Pooja Srinivas Nag
 * @author Mun Kei Wuai
 * @author Tan Wen Xiu
 */
public class CreateDeliveryInteractor implements CreateDeliveryContract.Interactor{
    private static final String TAG = CreateDeliveryInteractor.class.getSimpleName();

    private CreateDeliveryContract.OnCreateDeliveryListener mOnCreateDeliveryListener;

    public CreateDeliveryInteractor(CreateDeliveryContract.OnCreateDeliveryListener onCreateDeliveryListener){
        this.mOnCreateDeliveryListener = onCreateDeliveryListener;
    }

    // insert function that calls the database
}
