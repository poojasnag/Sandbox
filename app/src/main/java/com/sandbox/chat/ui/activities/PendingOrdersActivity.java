package com.sandbox.chat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

//import com.sandbox.chat.mgr.PendingOrdersMgr;
import com.sandbox.chat.R;
import com.sandbox.chat.ui.fragments.PendingOrdersFragment;
import com.sandbox.chat.core.pendingOrders.PendingOrdersPresenter;

/**
 * Allows buyers and deliverers to see the pending orders that are unique to them
 *
 * @author chua zi heng
 */
public class PendingOrdersActivity extends AppCompatActivity {
    PendingOrdersPresenter pendingOrdersController;
//    private Intent intent;
//    private LinkedList<String> orders;
//    private LinkedList<Transaction> transactionList;
    private Toolbar mToolbar;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PendingOrdersActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int flags) {
        Intent intent = new Intent(context, PendingOrdersActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pendingOrdersController = new PendingOrdersPresenter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_orders);
        bindViews();
        init();
//        final BottomNavigationView bot_bar = findViewById(R.id.pending_orders_bottomNavigationView);
//        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
    }

    private void bindViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void init() {
        // set the toolbar
        setSupportActionBar(mToolbar);

        // set the screen fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_content_pending_order,
                PendingOrdersFragment.newInstance(),
                PendingOrdersFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }
}

//    @Override
//    public void onStart() {
//        super.onStart();
//        intent = getIntent();
//        RecyclerView ordersList = findViewById(R.id.order_list);
//        if (intent.getSerializableExtra("user") instanceof Buyer){
//            pendingOrdersController.getOrders((User)intent.getSerializableExtra("user"), true, ordersList);
//        }
//        else{
//            pendingOrdersController.getOrders((User)intent.getSerializableExtra("user"), false, ordersList);
//        }
//
////        orders = new LinkedList<String>();  //TODO: get transaction objects
////        orders.add("xxyyzz \t\t 21 Jan 2021\nDeliver to: hall 7\n Eatery: koi, Pioneer\nMilk Tea with Pearl");
////        OrderDetailsAdapter adapter = new OrderDetailsAdapter(orders);
////        //TODO: pass the list of orders to this adapter
////        ordersList.setAdapter(adapter);
////        ordersList.setLayoutManager(new LinearLayoutManager(this));
//    }
//
//}

//TODO: Buyers and deliverers both will see pending orders, but the orders they see will be unique to them, need a Mgr