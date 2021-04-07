package com.sandbox.chat.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sandbox.chat.R;
import com.sandbox.chat.ui.contract.OrderCompleteContract;
import com.sandbox.chat.ui.contract.OrderIncompleteContract;
import com.sandbox.chat.ui.presenter.OrderCompletePresenter;
import com.sandbox.chat.ui.presenter.OrderIncompletePresenter;

public class OrderIncompleteFragment extends Fragment implements View.OnClickListener, OrderIncompleteContract.View{

    private Button rate_button;
    private ProgressDialog mProgressDialog;
    private OrderIncompletePresenter mOrderIncompletePresenter;

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
    }

    @Override
    public void onClick(View v) {

    }
}
