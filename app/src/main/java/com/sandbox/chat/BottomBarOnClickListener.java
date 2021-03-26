package com.sandbox.chat;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.ui.activities.ChatActivity;
import com.sandbox.chat.ui.activities.UserListingActivity;

public class BottomBarOnClickListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    public Context context;
    public BottomBarOnClickListener(@NonNull BottomNavigationView view)
    {
        super();
        context = view.getContext();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch(item.getItemId())
        {

            case R.id.botnav_chat:
                intent = new Intent(context, UserListingActivity.class);
                context.startActivity(intent);
            case R.id.botnav_pending:
                intent = new Intent(context, PendingOrdersActivity.class);
                context.startActivity(intent);
            case R.id.botnav_completed:
                intent = new Intent(context, ClosedOrderActivity.class);
                context.startActivity(intent);
            case R.id.botnav_settings:
                intent = new Intent(context, SettingsActivity.class);
                context.startActivity(intent);
            case R.id.botnav_home:
                intent = new Intent(context, EaterySelectionMapActivity.class);
                context.startActivity(intent);

            default:
                throw new IllegalStateException("Unexpected value: " + item.getTitle());
        }
    }
}
