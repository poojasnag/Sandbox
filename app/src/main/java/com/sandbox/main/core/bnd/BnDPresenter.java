package com.sandbox.main.core.bnd;

import com.sandbox.main.models.Buyer;
import com.sandbox.main.models.Deliverer;
import com.sandbox.main.models.Transaction;
import com.sandbox.main.models.User;
import com.sandbox.main.ui.fragments.BnDFragment;

import java.util.LinkedList;

/**
 * Presenter class for BnD activity
 */
public class BnDPresenter implements BnDContract.Presenter, BnDContract.onBnDListener{

    private BnDContract.View mbndView;
//    private bndInteractor mLoginInteractor;

    public BnDPresenter(){}
    public BnDPresenter(BnDContract.View bndView) {
        this.mbndView = bndView;
//        mLoginInteractor = new LoginInteractor(this);
    }

//    public void addRoleToDB {
//        bndInteractor.xxxx
//    }

    @Override
    public void onBuyerSuccess() {
        mbndView.onBuyerSelect(new BnDFragment().getContext());
    }

    @Override
    public void onDelivererSuccess() {
        mbndView.onDelivererSelect(new BnDFragment().getContext());
    }

    public static Buyer createBuyer(User user)
    {
        //TODO: Call TransactionInteractor to get all the transactions
        return new Buyer(user.getUid(), user.getEmail(), user.getFirebaseToken(), user.getRating() ,user.getRatingCount(), new LinkedList<Transaction>());
    }

    public static Deliverer createDeliverer(User user)
    {
        //TODO: Call TransactionInteractor to get all the transactions
        return new Deliverer(user.getUid(), user.getEmail(), user.getFirebaseToken(), user.getRating(), user.getRatingCount(),new LinkedList<Transaction>());
    }
}
