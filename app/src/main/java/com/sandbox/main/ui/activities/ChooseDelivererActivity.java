package com.sandbox.main.ui.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.main.R;
import com.sandbox.main.core.chooseDeliverer.ChooseDelivererPresenter;
import com.sandbox.main.ui.BottomBarOnClickListener;
import com.sandbox.main.ui.fragments.ChooseDelivererFragment;

import java.util.LinkedList;

/**
 * For the buyers, displays the list of active deliverers in a location
 * @author Chua Zi Heng
 * @author Luong Minh Quang
 */
public class ChooseDelivererActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    private Intent i;
    private static final String TAG = "ImportDB";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_DELIVERYFEE = "deliveryFee";
    private static final String KEY_NAME = "email";
//    private Button selectedLocation;
    LinkedList<String> demo  = new LinkedList<String>();
    ChooseDelivererPresenter chooseDelivererPresenter;
//    private RecyclerView ordersList;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ChooseDelivererActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_deliverer);
//        chooseDelivererController = new ChooseDelivererMgr(this);
        final BottomNavigationView bot_bar = findViewById(R.id.choose_deliverer_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        bindViews();
        init();
//        // new zh code
//        selectedLocation = findViewById(R.id.choose_deliverer_button);
//        ordersList = findViewById(R.id.choose_deliverer_list);
    }
    private void bindViews() { mToolbar = (Toolbar) findViewById(R.id.toolbar);}

    private void init(){
        // set the toolbar
        setSupportActionBar(mToolbar);

        // set the choose deliverer screen fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_content_choose_deliverer,
                ChooseDelivererFragment.newInstance(),
                ChooseDelivererFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

//    @Override
//    protected void onStart()
//    {
//        super.onStart();
//        i = getIntent();
//        Eatery curEatery = (Eatery) (i.getSerializableExtra("Eatery"));
//        chooseDelivererPresenter.setLocation(selectedLocation, i);
//        chooseDelivererPresenter.getDeliverers(ordersList, curEatery);
//
//        RecyclerView ordersList = findViewById(R.id.choose_deliverer_list);
//
//        // chooseDelivererController.getDeliverers(ordersList); // zh new code doesnt have this
//
////        demo.add("Name: xxabcxx \nRate:$0.5\nLocation 1: Hall 7 bus stop");
////        demo.add("Name: test2 \nRate:$0.5\nLocation 1: Hall 7 bus stop");
////        Log.d(TAG, demo.toString());  // document.getId() document.getData()
//
//    }
//    public void setDelivererList(LinkedList<String> demo){
//        this.demo = demo;
//    }
}