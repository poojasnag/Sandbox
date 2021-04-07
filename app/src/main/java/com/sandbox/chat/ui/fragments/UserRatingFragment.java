package com.sandbox.chat.ui.fragments;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.sandbox.chat.R;
import com.sandbox.chat.ui.contract.UserRatingContract;
import com.sandbox.chat.ui.presenter.UserRatingPresenter;
//import com.sandbox.chat.mgr.UserRatingMgr;
import com.sandbox.chat.ui.activities.EaterySelectionMapActivity;
import com.sandbox.chat.ui.activities.OrderCompleteActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserRatingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserRatingFragment extends Fragment implements View.OnClickListener, UserRatingContract.View{
    private static final String TAG = UserRatingFragment.class.getSimpleName();
    private UserRatingPresenter mUserRatingPresenter;
    Intent i;
    private RatingBar mRatingBar;
    private EditText mETxtComments;
    private Button mBtnSubmit;

    private ProgressDialog mProgressDialog;
    
//    UserRatingMgr userRatingController;


//    public UserRatingFragment() {
//        // Required empty public constructor
//    }

    public static UserRatingFragment newInstance() {
        Bundle args = new Bundle();
        UserRatingFragment fragment = new UserRatingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onStart()
    {
        super.onStart();
    }
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_user_rating, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    private void bindViews(View view) {
        mRatingBar = (RatingBar) view.findViewById(R.id.user_rating_bar);
        mETxtComments = (EditText) view.findViewById(R.id.user_rating_comment_box);
        mBtnSubmit = (Button) view.findViewById(R.id.user_rating_submit);
        i = getActivity().getIntent();
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        init();
    }
    
    private void init(){
//        mUserRatingPresenter = new UserRatingPresenter(this);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);
        mBtnSubmit.setOnClickListener(this);

        mBtnSubmit.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View view){
        int viewId = view.getId();
        switch(viewId){
            case R.id.user_rating_submit:
                onSubmitSelect(view.getContext());
                break;
        }
    }
    
    public void onSubmitSelect(Context context){
//        userRatingController = new UserRatingMgr(this);
        mProgressDialog.dismiss();
//        int rating = 5;
//        String comments = mETxtComments.getText().toString();
//        mUserRatingPresenter.submitRating(getActivity(), rating, comments);
        Toast.makeText(getContext(), "Rating submitted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(i);
        intent.setComponent(new ComponentName(context, EaterySelectionMapActivity.class));
        startActivity(intent);
    }



    
//        userRatingController = new UserRatingMgr(this);
//        Button sendRating = findViewById(R.id.user_rating_submit);
//        sendRating.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), EaterySelectionMapActivity.class);
//                startActivity(intent);
//            }
//        });
}