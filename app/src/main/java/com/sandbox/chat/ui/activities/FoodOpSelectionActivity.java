package com.sandbox.chat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.R;
import com.sandbox.chat.mgr.FoodOpSelectionMgr;

/**
 * Alternative method for users to pick an eatery through a spinner
 *
 * @author chua zi heng
 */

import com.sandbox.chat.models.Eatery;


/**
 * 
 */
public class FoodOpSelectionActivity extends AppCompatActivity {

    FoodOpSelectionMgr foodOpSelectionController;
    Spinner optionList;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, FoodOpSelectionActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int flags) {

        Intent intent = new Intent(context, FoodOpSelectionActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_op_selection);
//        foodOpSelectionController = new FoodOpSelectionMgr();
        final BottomNavigationView bot_bar = findViewById(R.id.food_op_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        //TODO: Connect the drop-down list with menu from the stores


    }




}