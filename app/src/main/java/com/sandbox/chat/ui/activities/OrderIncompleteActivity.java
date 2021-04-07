package com.sandbox.chat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.sandbox.chat.R;
import com.sandbox.chat.ui.fragments.OrderCompleteFragment;
import com.sandbox.chat.ui.fragments.OrderIncompleteFragment;

public class OrderIncompleteActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, OrderIncompleteActivity.class);
//        intent.putExtra("user", user);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int flags) {
        Intent intent = new Intent(context, OrderIncompleteActivity.class);
//        intent.putExtra("user", user);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        bndController = new BnDPresenter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_incomplete);
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
        fragmentTransaction.replace(R.id.frame_layout_content_order_incomplete,
                OrderIncompleteFragment.newInstance(),
                OrderIncompleteFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }
}
