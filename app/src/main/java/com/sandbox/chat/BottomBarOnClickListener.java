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
        CharSequence title = item.getTitle();
        if ("Chats".equals(title)) {
            intent = new Intent(context, UserListingActivity.class);
            context.startActivity(intent);
            return true;
        } else if ("Pending orders".equals(title)) {
            intent = new Intent(context, PendingOrdersActivity.class);
            context.startActivity(intent);
            return true;
        } else if ("Completed orders".equals(title)) {
            intent = new Intent(context, ClosedOrderActivity.class);
            context.startActivity(intent);
            return true;
        } else if ("Settings".equals(title)) {
            intent = new Intent(context, SettingsActivity.class);
            context.startActivity(intent);
            return true;
        } else if ("Home".equals(title)) {
            intent = new Intent(context, EaterySelectionMapActivity.class);
            context.startActivity(intent);
            return true;
        }
        throw new IllegalStateException("Unexpected value: " + item.getTitle() + "\nID:" + item.getItemId() + "Home ID:" + R.id.botnav_home);
    }
}
