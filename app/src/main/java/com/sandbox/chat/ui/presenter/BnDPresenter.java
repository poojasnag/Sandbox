package com.sandbox.chat.ui.presenter;

import com.sandbox.chat.core.login.LoginContract;
import com.sandbox.chat.core.login.LoginInteractor;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.Transaction;
import com.sandbox.chat.models.User;
import com.sandbox.chat.ui.activities.BnDActivity;
import com.sandbox.chat.ui.contract.BnDContract;
import com.sandbox.chat.ui.fragments.BnDFragment;

import java.util.LinkedList;

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
        //TODO: Call TransactionMgr to get all the transactions
        return new Buyer(user.getUid(), user.getEmail(), user.getFirebaseToken(), user.getRating() , new LinkedList<Transaction>());
    }

    public static Deliverer createDeliverer(User user)
    {
        //TODO: Call TransactionMgr to get all the transactions
        return new Deliverer(user.getUid(), user.getEmail(), user.getFirebaseToken(), user.getRating(), new LinkedList<Transaction>());
    }
}
