package com.sandbox.main.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sandbox.main.R;
import com.sandbox.main.core.chooseDeliverer.ChooseDelivererContract;
import com.sandbox.main.core.chooseDeliverer.ChooseDelivererPresenter;
import com.sandbox.main.models.Eatery;

/**
 * View holder of ChooseDelivererActivity
 * @author Chua Zi Heng
 */
public class ChooseDelivererFragment extends Fragment implements View.OnClickListener, ChooseDelivererContract.View{
    private static final String FINAL = ChooseDelivererFragment.class.getSimpleName();

    private Intent i;
    private Button mBtnChooseDeliverer;
    private RecyclerView mRecyclerViewDelivererList;
    private ChooseDelivererPresenter mChooseDelivererPresenter;

    private ProgressDialog mProgressDialog;

    public static ChooseDelivererFragment newInstance() {
        Bundle args = new Bundle();
        ChooseDelivererFragment fragment = new ChooseDelivererFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Populates the list with deliverers' information
     * Called directly after onCreate(), and whenever the user navigates to this activity from another activity
     */
    @Override
    public void onStart()
    {
        super.onStart();
        i = getActivity().getIntent();
        Eatery curEatery = (Eatery) (i.getSerializableExtra("Eatery"));

        // review where to add this
        mBtnChooseDeliverer = mBtnChooseDeliverer.findViewById(R.id.choose_deliverer_button);
        mRecyclerViewDelivererList = mRecyclerViewDelivererList.findViewById(R.id.choose_deliverer_list);

        mChooseDelivererPresenter.setLocation(mBtnChooseDeliverer, i);
        mChooseDelivererPresenter.getDeliverers(mRecyclerViewDelivererList, curEatery, i);
    }

    // done
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_choose_deliverer, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    private void bindViews(View view) {
        mRecyclerViewDelivererList = (RecyclerView) view.findViewById(R.id.choose_deliverer_list);
        mBtnChooseDeliverer = (Button) view.findViewById(R.id.choose_deliverer_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init(){
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle("Loading");
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

        mChooseDelivererPresenter = new ChooseDelivererPresenter(this);
        // get message from presenter??

        mBtnChooseDeliverer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        int viewId = view.getId();

        switch (viewId) {
            case R.id.choose_deliverer_button:
                onChooseDeliverer(view);
                break;
        }
    }

    private void onChooseDeliverer(View view){
        // input
//        Toast.makeText(getContext(), "Deliverer Chosen", Toast.LENGTH_SHORT).show();
    }
}