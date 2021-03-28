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
import com.sandbox.chat.models.Eatery;

public class FoodOpSelectionActivity extends AppCompatActivity {

    private final FoodOpSelectionMgr foodOpSelectionMgr = new FoodOpSelectionMgr();
    Spinner optionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_op_selection);
        final BottomNavigationView bot_bar = findViewById(R.id.choose_deliverer_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        //TODO: Connect the drop-down list with menu from the stores

        optionList = findViewById(R.id.food_option_list);
        optionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                foodOpSelectionMgr.selectItem(new Eatery("TODO",0,0));
                //TODO: REplace with actual eatery
            }
        });
    }




}