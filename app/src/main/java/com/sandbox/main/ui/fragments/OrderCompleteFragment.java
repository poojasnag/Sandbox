package com.sandbox.main.ui.fragments;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sandbox.main.R;
import com.sandbox.main.core.orderComplete.OrderCompletePresenter;
import com.sandbox.main.interactors.TransactionInteractor;
import com.sandbox.main.models.Buyer;
import com.sandbox.main.models.Status;
import com.sandbox.main.models.Transaction;
import com.sandbox.main.ui.activities.UserRatingActivity;

/**
 * View holder of OrderCompleteActivity
 * @see com.sandbox.main.ui.activities.OrderCompleteActivity
 * @author Mun Kei Wuai
 */
public class OrderCompleteFragment extends Fragment implements View.OnClickListener { //, OrderCompleteContract.View

    private Button rate_button;
    private ProgressDialog mProgressDialog;
    private OrderCompletePresenter mOrderCompletePresenter;
    Intent i;

    public static OrderCompleteFragment newInstance() {
        Bundle args = new Bundle();
        OrderCompleteFragment fragment = new OrderCompleteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_order_complete, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    private void bindViews(View view) {
        i = getActivity().getIntent();
        rate_button = (Button) view.findViewById(R.id.button_rate);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        mOrderCompletePresenter = new OrderCompletePresenter();

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);
        rate_button.setOnClickListener(this);
//        mOrderCompletePresenter.updateOrderStatus();   //TODO:temporarily detatch from presenter

        updateStatus(getContext());
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.button_rate:
                onSelectRate(view.getContext());
                break;
        }
    }

    /**
     * Called when the user selects the "Rate" button
     * Directs the user to the rating interface
     * @param context An instance of OrderCompleteActivity
     */
    public void onSelectRate(Context context) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), "You have selected RATE", Toast.LENGTH_SHORT).show();
        Transaction transaction = (Transaction) i.getSerializableExtra("Transaction");
        Intent intent = new Intent(i);
        intent.setComponent(new ComponentName(context, UserRatingActivity.class));
        startActivity(intent);
    }

    /**
     * Updates the completion status on the database
     * @param context An instance of the OrderCompleteActivity
     */
    public void updateStatus(Context context){
        Transaction transaction = (Transaction) i.getSerializableExtra("Transaction");
        if (i.getSerializableExtra("user") instanceof Buyer) {
            TransactionInteractor.updateStatus(true, "buyerStatus", transaction.getTransactionID());
            transaction.setBuyerStatus(Status.COMPLETE);
        } else {
            TransactionInteractor.updateStatus(true, "delivererStatus", transaction.getTransactionID());
            transaction.setDelivererStatus(Status.COMPLETE);
        }

    }

}
