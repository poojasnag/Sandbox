package com.sandbox.main.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.sandbox.main.R;
import com.sandbox.main.core.bnd.BnDPresenter;
import com.sandbox.main.interactors.UserInteractor;
import com.sandbox.main.models.User;
import com.sandbox.main.ui.fragments.BnDFragment;
import com.sandbox.main.utils.Constants;
import com.sandbox.main.utils.SharedPrefUtil;

//import com.sandbox.chat.mgr.BnDMgr;

/**
 * Displays the Buyer and Deliverer selection interface
 * @author Luong Minh Quang
 * @author Chua Zi Heng
 */

public class BnDActivity extends AppCompatActivity {
    BnDPresenter bndController ;
    private Toolbar mToolbar;

    public static void startActivity(Context context, FirebaseUser fUser) {
        UserInteractor.getUserDocument(fUser.getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    User curUser =  new User(fUser.getUid() ,fUser.getEmail(), new SharedPrefUtil(context).getString(Constants.ARG_FIREBASE_TOKEN), document.getLong("rating").intValue(),document.getLong("ratingCount").intValue());
                    Intent intent = new Intent(context, BnDActivity.class);
                    intent.putExtra("user", curUser);
                    context.startActivity(intent);
                }
            }
        });

    }


    public static void startActivity(Context context, FirebaseUser fUser, int flags) {
        UserInteractor.getUserDocument(fUser.getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    User curUser =  new User(fUser.getUid() ,fUser.getEmail(), new SharedPrefUtil(context).getString(Constants.ARG_FIREBASE_TOKEN), document.getLong("rating").intValue(),document.getLong("ratingCount").intValue());
                    Intent intent = new Intent(context, BnDActivity.class);
                    intent.putExtra("user", curUser);
                    intent.setFlags(flags);
                    context.startActivity(intent);
                }
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bndController = new BnDPresenter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bn_d);
        bindViews();
        init();

    }
    private void bindViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void init() {
        // set the toolbar
        setSupportActionBar(mToolbar);

        // set the screen fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_content_bnd,
                BnDFragment.newInstance(),
                BnDFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }


}