package com.sandbox.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EaterySelectionMapActivity extends AppCompatActivity {
    public Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatery_selection_map);

    }
    @Override
    protected void onStart()
    {
        super.onStart();
        i = getIntent();
    }

    protected void SelectLocation(View view)
    {
        if(i.getBooleanExtra("isBuyer", true))
        {
            Intent intent = new Intent(i);
            intent.setComponent(new ComponentName(view.getContext(), ChooseDelivererActivity.class));
            startActivity(intent);

        }
        else
        {
            Intent intent = new Intent(i);
            intent.setComponent(new ComponentName(view.getContext(), CreateDeliveryActivity.class));
            startActivity(intent);
        }

    }

}