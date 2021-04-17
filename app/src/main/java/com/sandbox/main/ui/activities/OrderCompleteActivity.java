package com.sandbox.main.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.sandbox.main.R;
import com.sandbox.main.ui.fragments.OrderCompleteFragment;

/**
 * After the user mark an order as complete, displays an order completion page and prompt the user to give a rating to the other user
 * @author Mun Kei Wuai
 */
public class OrderCompleteActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, OrderCompleteActivity.class);
//        intent.putExtra("user", user);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int flags) {
        Intent intent = new Intent(context, OrderCompleteActivity.class);
//        intent.putExtra("user", user);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        bndController = new BnDPresenter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
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
        fragmentTransaction.replace(R.id.frame_layout_content_order_complete,
                OrderCompleteFragment.newInstance(),
                OrderCompleteFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }
}
