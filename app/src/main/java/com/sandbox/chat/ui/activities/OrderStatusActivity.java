package com.sandbox.chat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.sandbox.chat.mgr.OrderStatusMgr;
import com.sandbox.chat.R;
import com.sandbox.chat.ui.fragments.OrderStatusFragment;
import com.sandbox.chat.core.orderStatus.OrderStatusPresenter;



/**
 * Users are directed to this view after the buyer places their order, where users can view their order summary, chat with the other user, and indicate completion
 *
 * @author chua zi heng
 */
public class OrderStatusActivity extends AppCompatActivity {
    OrderStatusMgr orderStatusController;
    private OrderStatusPresenter orderStatusPresenter;

    private Toolbar mToolbar;
    private Intent i;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, OrderStatusActivity.class);
        context.startActivity(intent);
    }
    public static void startActivity(Context context, int flags) {

        Intent intent = new Intent(context, OrderStatusActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        orderStatusPresenter = new OrderStatusPresenter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        bindViews();
        init();
//        orderStatusController = new OrderStatusMgr();
    }


    protected void bindViews()
    {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void init() {
        // set the toolbar
        setSupportActionBar(mToolbar);

        // set the screen fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_content_order_status,
                OrderStatusFragment.newInstance(),
                OrderStatusFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }
}