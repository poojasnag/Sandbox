package com.sandbox.chat.ui;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.R;
import com.sandbox.chat.ui.activities.ClosedOrderActivity;
import com.sandbox.chat.ui.activities.MapsActivity;
import com.sandbox.chat.ui.activities.PendingOrdersActivity;
import com.sandbox.chat.ui.activities.SettingsActivity;
import com.sandbox.chat.ui.activities.UserListingActivity;

/**
 * Sets the behavior of the bottom navigation bar in every interfaces
 */
public class BottomBarOnClickListener implements BottomNavigationView.OnNavigationItemSelectedListener {
    /**
     * The context of the activity in which the user clicked on any element of the bottom navigation bar.
     */
    public Context context;

    /**
     * Initialize the on-click listener
     * @param view the bottom navigation bar that the user clicked on
     */
    public BottomBarOnClickListener(@NonNull BottomNavigationView view)
    {
        super();
        context = view.getContext();
    }

    /**
     * Reroutes the user to the corresponding activity
     * @param item The element in the bottom navigation bar that the user clicked on
     * @return true if successful
     */
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
            intent = new Intent(context, MapsActivity.class);
            context.startActivity(intent);
            return true;
        }
        throw new IllegalStateException("Unexpected value: " + item.getTitle() + "\nID:" + item.getItemId() + "Home ID:" + R.id.botnav_home);
    }
}
