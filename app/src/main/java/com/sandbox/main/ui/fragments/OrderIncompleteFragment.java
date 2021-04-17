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
import com.sandbox.main.core.orderIncomplete.OrderIncompleteContract;
import com.sandbox.main.core.orderIncomplete.OrderIncompletePresenter;
import com.sandbox.main.interactors.TransactionInteractor;
import com.sandbox.main.models.Buyer;
import com.sandbox.main.models.Status;
import com.sandbox.main.models.Transaction;
import com.sandbox.main.ui.activities.UserRatingActivity;

/**
 * View holder for OrderIncomplete activity
 * @see com.sandbox.main.ui.activities.OrderCompleteActivity
 * @author Mun Kei Wuai
 */
public class OrderIncompleteFragment extends Fragment implements View.OnClickListener, OrderIncompleteContract.View{

    private Button rate_button;
    private ProgressDialog mProgressDialog;
    private OrderIncompletePresenter mOrderIncompletePresenter;
    Intent i;

    public static OrderIncompleteFragment newInstance() {
        Bundle args = new Bundle();
        OrderIncompleteFragment fragment = new OrderIncompleteFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_order_incomplete, container, false);
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
        mOrderIncompletePresenter = new OrderIncompletePresenter();

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

        rate_button.setOnClickListener(this);
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
     * Called when the user clicks on "rate" button
     * Directs the user to the rating interface
     * @param context An instance of OrderIncompleteActivity
     */
    public void onSelectRate(Context context) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), "You have selected RATE", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(i);
        intent.setComponent(new ComponentName(context, UserRatingActivity.class));
        startActivity(intent);
    }

    /**
     * Updates the completion status on the database
     * @param context An instance of the OrderIncompleteActivity
     */
    public void updateStatus(Context context){
        Transaction transaction = (Transaction) i.getSerializableExtra("Transaction");
        if (i.getSerializableExtra("user") instanceof Buyer) {
            TransactionInteractor.updateStatus(false, "buyerStatus", transaction.getTransactionID());
            transaction.setBuyerStatus(Status.INCOMPLETE);
        } else {
            TransactionInteractor.updateStatus(false, "delivererStatus", transaction.getTransactionID());
            transaction.setDelivererStatus(Status.INCOMPLETE);
        }

    }
}
