package com.sandbox.main.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.main.R;
import com.sandbox.main.ui.activities.ClosedOrderActivity;
import com.sandbox.main.ui.activities.MapsActivity;
import com.sandbox.main.ui.activities.PendingOrdersActivity;
import com.sandbox.main.ui.activities.SettingsActivity;
import com.sandbox.main.ui.activities.UserListingActivity;

/**
 * Sets the behavior of the bottom navigation bar in every interfaces
 * @author Luong Minh Quang
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
        Intent prevIntent = ((Activity) context).getIntent();
        CharSequence title = item.getTitle();
        if ("Chats".equals(title)) {
            intent = new Intent(prevIntent);
            intent.setComponent(new ComponentName(context, UserListingActivity.class));
            context.startActivity(intent);
            return true;
        } else if ("Pending".equals(title)) {
            intent = new Intent(prevIntent);
            intent.setComponent(new ComponentName(context, PendingOrdersActivity.class));
            context.startActivity(intent);
            return true;
        } else if ("Completed".equals(title)) {
            intent = new Intent(prevIntent);
            intent.setComponent(new ComponentName(context, ClosedOrderActivity.class));
            context.startActivity(intent);
            return true;
        } else if ("Settings".equals(title)) {
            intent = new Intent(prevIntent);
            intent.setComponent(new ComponentName(context, SettingsActivity.class));
            context.startActivity(intent);
            return true;
        } else if ("Home".equals(title)) {
            intent = new Intent(prevIntent);
            intent.setComponent(new ComponentName(context, MapsActivity.class));
            context.startActivity(intent);
            return true;
        }
        throw new IllegalStateException("Unexpected value: " + item.getTitle() + "\nID:" + item.getItemId() + "Home ID:" + R.id.botnav_home);
    }
}