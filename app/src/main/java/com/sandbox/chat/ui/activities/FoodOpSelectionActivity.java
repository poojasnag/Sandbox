package com.sandbox.chat.ui.activities;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sandbox.chat.core.maps.MapsInteractor;
import com.sandbox.chat.interactors.DelivererOfferInteractor;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.User;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.R;

/**
 * Alternative method for users to pick an eatery through a spinner
 *
 * @author chua zi heng
 */

import com.sandbox.chat.models.Eatery;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;


/**
 *
 */
public class FoodOpSelectionActivity extends AppCompatActivity {

    private LinkedList<String> eateryNames;
    private ListView eateryList;
    private ArrayAdapter<String> adapter;


    public Dialog getLocationDetails() {
        return locationDetails;
    }

    private Dialog locationDetails;
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

        init();

        //TODO: Connect the drop-down list with menu from the stores
    }


    private void init() {
        final BottomNavigationView bot_bar = findViewById(R.id.food_op_bottomNavigationView);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));
        locationDetails = new Dialog(this);
        eateryNames = MapsInteractor.getEateryNames();
        eateryList = findViewById(R.id.food_op_eatery_list);
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, eateryNames);
        eateryList.setAdapter(adapter);
        SearchView searchView = findViewById(R.id.food_op_search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });
        eateryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                showLocationDetails(name);
            }
        });


    }



    /**
     * Moves to the next activity.
     * If the user is currently a buyer, move to ChooseDelivererActivity
     * If the user is currently a deliverer, move to CreateDeliveryActivity
     * @param view  An instance of MapsActivity
     * @param eatery The selected eatery
     */
    public void selectLocation(View view, Eatery eatery) {
        //TODO: Add the information of the location
        if (getIntent().getSerializableExtra("user") instanceof Buyer) {
            Intent intent = new Intent(getIntent());
            intent.setComponent(new ComponentName(view.getContext(), ChooseDelivererActivity.class));
            if(intent.hasExtra("Eatery")) {
                intent.removeExtra("Eatery");
            }
            intent.putExtra("Eatery", eatery);
            startActivity(intent);


        } else {
            Intent intent = new Intent(getIntent());
            intent.setComponent(new ComponentName(view.getContext(), CreateDeliveryActivity.class));
            if(intent.hasExtra("Eatery")) {
                intent.removeExtra("Eatery");
            }
            intent.putExtra("Eatery", eatery);
            startActivity(intent);
        }
    }
    /**
     * Displays a pop-up showing the location details
     * @param name The name of the eatery that the user selected
     */
    public void showLocationDetails(String name)
    {
        Eatery e = MapsInteractor.findEatery(name, true);
        TextView txtclose;
        Button btnFollow;
        Dialog myDialog = getLocationDetails();
        myDialog.setContentView(R.layout.eatery_details);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        Intent i = getIntent();


        TextView eateryName = myDialog.findViewById(R.id.eatery_name);
        TextView eateryLoc = myDialog.findViewById(R.id.eatery_addresss);
        TextView eateryTime = myDialog.findViewById(R.id.eatery_op_time);
        TextView delivererCount = myDialog.findViewById(R.id.eatery_details_num_deliverers);
        eateryName.setText(e.getEateryName());
        eateryLoc.setText(e.getEateryAddress() + ", " + e.getEateryStreet());
        eateryTime.setText(e.getOperatingTime());
        DelivererOfferInteractor.getEateryDeliverers(e).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            Integer count = 0;
                            LocalDateTime now = LocalDateTime.now();
                            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                LocalDateTime cutoffDT = LocalDateTime.parse(document.getString("cutoffDateTime"), f);
                                boolean delivererIsYou = ((User) i.getSerializableExtra("user")).getUid().equals(document.getString("delivererID")) ;

                                if ((!cutoffDT.isBefore(now)) && !delivererIsYou){
                                    count++;
                                }
                            }
                            delivererCount.setText(count.toString());


                        }
                        else {
                            delivererCount.setText("0");
                        }
                    }
                });


        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLocation(view, e);
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }





}