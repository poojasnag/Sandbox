package com.sandbox.chat.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.R;
import com.sandbox.chat.mgr.FoodOpSelectionMgr;

/**
 * Alternative method for users to pick an eatery through a spinner
 *
 * @author chua zi heng
 */

import com.sandbox.chat.models.Eatery;

public class FoodOpSelectionActivity extends AppCompatActivity {

    FoodOpSelectionMgr foodOpSelectionController;
    Spinner optionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_op_selection);
        foodOpSelectionController = new FoodOpSelectionMgr();
        final BottomNavigationView bot_bar = findViewById(R.id.choose_deliverer_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        //TODO: Connect the drop-down list with menu from the stores

        optionList = findViewById(R.id.food_option_list);
        optionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                foodOpSelectionController.selectItem(new Eatery("0","TODO","", "", ""));
                //TODO: REplace with actual eatery
            }
        });
    }




}