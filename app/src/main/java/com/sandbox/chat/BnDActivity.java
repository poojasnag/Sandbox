package com.sandbox.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.sandbox.chat.ui.activities.UserListingActivity;

public class BnDActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, BnDActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int flags) {
        Intent intent = new Intent(context, BnDActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bn_d);

// TODO: Link the buttons on bottom nav bar with activities
        Button buyer_button = findViewById(R.id.bnd_button_buyer);
        Button deliverer_button = findViewById(R.id.bnd_button_deliverer);

    }

}