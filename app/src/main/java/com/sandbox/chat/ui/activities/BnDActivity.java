package com.sandbox.chat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.sandbox.chat.R;
import com.sandbox.chat.mgr.BnDMgr;
import com.sandbox.chat.models.User;
import com.sandbox.chat.ui.fragments.BnDFragment;
import com.sandbox.chat.ui.fragments.LoginFragment;

/**
 * Displays the Buyer and Deliverer selection interface
 */

public class BnDActivity extends AppCompatActivity {
    BnDMgr bndController ;
    private Toolbar mToolbar;
    /**
     * Displays the interface from another activity class
     * @param context the Context of the activity that called this method
     */
    public static void startActivity(Context context, User user) {
        Intent intent = new Intent(context, BnDActivity.class);
        intent.putExtra("user", user);
        context.startActivity(intent);
    }

    /**
     * Displays the interface from another activity class
     * @param context the Context of the activity that called this method
     * @param flags flags to pass to the Intent before starting the activity
     */
    public static void startActivity(Context context, User user, int flags) {

        Intent intent = new Intent(context, BnDActivity.class);
        intent.putExtra("user", user);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    /**
     * Initialize the interface:
     *  + loading the corresponding layout file
     *  + Binding the on-click listener to the buttons.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bndController = new BnDMgr();
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