package com.sandbox.chat.ui.fragments;

import android.app.ProgressDialog;
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

import com.sandbox.chat.R;
import com.sandbox.chat.ui.contract.BnDContract;
import com.sandbox.chat.mgr.BnDMgr;
import com.sandbox.chat.models.User;
import com.sandbox.chat.ui.activities.BnDActivity;
import com.sandbox.chat.ui.activities.EaterySelectionMapActivity;
import com.sandbox.chat.ui.presenter.BnDPresenter;
import com.sandbox.chat.models.User;

public class BnDFragment extends Fragment implements View.OnClickListener, BnDContract.View {
    private BnDPresenter mBnDPresenter;
    private Button buyer_button, deliverer_button;
    private ProgressDialog mProgressDialog;
    BnDPresenter bndController ;

    public static BnDFragment newInstance() {
        Bundle args = new Bundle();
        BnDFragment fragment = new BnDFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_bn_d, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    private void bindViews(View view) {
        buyer_button = (Button) view.findViewById(R.id.bnd_button_buyer);
        deliverer_button = (Button) view.findViewById(R.id.bnd_button_deliverer);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        mBnDPresenter = new BnDPresenter((com.sandbox.chat.ui.contract.BnDContract.View) this);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

        buyer_button.setOnClickListener(this);
        deliverer_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        switch (viewId) {
            case R.id.bnd_button_buyer:
                onBuyerSelect(view.getContext());
                break;
            case R.id.bnd_button_deliverer:
                onDelivererSelect(view.getContext());
                break;
        }
    }
    public void onDelivererSelect(Context context) {
        bndController = new BnDPresenter();
        mProgressDialog.dismiss();
        Intent intent = new Intent(context, EaterySelectionMapActivity.class);
        intent.putExtra("user", bndController.createDeliverer((User) getActivity().getIntent().getSerializableExtra("user")));
        Toast.makeText(getContext(), "You have selected DELIVERER", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void onBuyerSelect(Context context){
        bndController = new BnDPresenter();
        mProgressDialog.dismiss();
        Intent intent = new Intent(context, EaterySelectionMapActivity.class);
        intent.putExtra("user", bndController.createBuyer((User) getActivity().getIntent().getSerializableExtra("user")));
        Toast.makeText(getContext(), "You have selected BUYER", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}



//        buyer_button.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//            onBuyerSelect(view.getContext());
//        }
//    });
//        deliverer_button.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view)
//        {
//            onDelivererSelect(view.getContext());
//        }
//    });
//    private void onDelivererSelect(Context context) {
//        mProgressDialog.dismiss();
//        Intent intent = new Intent(context, EaterySelectionMapActivity.class);
//        intent.putExtra("user", BnDMgr.createDeliverer((User) getIntent().getSerializableExtra("user")));
//        startActivity(intent);
//    }
//
//    public void onBuyerSelect(Context context){
//        mProgressDialog.dismiss();
//        Intent intent = new Intent(context, EaterySelectionMapActivity.class);
//        intent.putExtra("user", BnDMgr.createBuyer((User) getIntent().getSerializableExtra("user")));
//        Toast.makeText(getContext(), "im in buyerselect", Toast.LENGTH_SHORT).show();
//        startActivity(intent);
//    }