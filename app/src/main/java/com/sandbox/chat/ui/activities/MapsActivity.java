package com.sandbox.chat.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.maps.CameraUpdateFactory;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.LatLng;
import com.google.android.libraries.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Layer;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.sandbox.chat.adapters.UserListingPagerAdapter;
import com.sandbox.chat.core.logout.LogoutContract;
import com.sandbox.chat.core.logout.LogoutPresenter;
import com.sandbox.chat.core.maps.MapsInteractor;
import com.sandbox.chat.core.maps.MapsPresenter;
import com.sandbox.chat.core.settings.SettingsInteractor;
import com.sandbox.chat.mgr.DelivererOfferMgr;
import com.sandbox.chat.models.Buyer;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.R;
import com.sandbox.chat.utils.Constants;

//import com.sandbox.chat.ui.fragments.MapsFragment;

import org.json.JSONException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Displays the eatery selection interface
 */
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, LogoutContract.View, View.OnClickListener {

    private GoogleMap mMap;
    private GeoJsonLayer layer1;
    MapsPresenter c;
    public Intent i;


    private LogoutPresenter mLogoutPresenter;

    FusedLocationProviderClient client;
    SupportMapFragment supportMapFragment;


    public Dialog getLocationDetails() {
        return locationDetails;
    }

    Dialog locationDetails;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MapsActivity.class);
        context.startActivity(intent);
    }

    @Override

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(SettingsInteractor.getMapType());

        MapsInteractor.initialize(this);


        //import geojson file into map
        try {
            GeoJsonLayer layer = new GeoJsonLayer(mMap, R.raw.raw, this);
//            GeoJsonPolygonStyle polyStyle = layer.getDefaultPolygonStyle();
//            polyStyle.setStrokeColor(Color.CYAN);
//            polyStyle.setStrokeWidth(2);
            layer.addLayerToMap();
            layer.setOnFeatureClickListener(new Layer.OnFeatureClickListener() {
                @Override
                public void onFeatureClick(Feature feature) {
                    System.out.println(feature.getProperty("Name"));
                    Log.e("getEateryNameJSON", feature.getProperty("Name"));
                    showLocationDetails(feature.getProperty("Name"));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_listing, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void onCreate(Bundle savedInstanceState) {

        mLogoutPresenter = new LogoutPresenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatery_selection_map);

            MapsPresenter c = new MapsPresenter(this);

        final BottomNavigationView bot_bar = findViewById(R.id.eatery_selection_botnav);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.eatery_selection_map);
        mapFragment.getMapAsync(this);
        locationDetails = new Dialog(this);

        //initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this);

        //check permission
        if (ActivityCompat.checkSelfPermission(MapsActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }
        else {
            //when permission denied, request permission
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClickSearch(Context context) {
        Intent intent = new Intent(i);
        intent.setComponent(new ComponentName(context, FoodOpSelectionActivity.class));
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch(viewId) {
            case R.id.dummy_search:
                onClickSearch(this);
                break;
        }
    }

    public void selectLocation(View view, Eatery eatery) {
        //TODO: Add the information of the location
        if (getIntent().getSerializableExtra("user") instanceof Buyer) {
            Intent intent = new Intent(getI());
            intent.setComponent(new ComponentName(view.getContext(), ChooseDelivererActivity.class));
            if(intent.hasExtra("Eatery")) {
                intent.removeExtra("Eatery");
            }
            intent.putExtra("Eatery", eatery);
            startActivity(intent);


        } else {
            Intent intent = new Intent(getI());
            intent.setComponent(new ComponentName(view.getContext(), CreateDeliveryActivity.class));
            if(intent.hasExtra("Eatery")) {
                intent.removeExtra("Eatery");
            }
            intent.putExtra("Eatery", eatery);
            startActivity(intent);
        }
    }

    public void showLocationDetails(String feature_id)
    {
        //from EaterySelectionMapMgr.java

        String ID = feature_id;

        Eatery e = MapsInteractor.findEatery(ID);
        TextView txtclose;
        Button btnFollow;
        Dialog myDialog = getLocationDetails();
        myDialog.setContentView(R.layout.eatery_details);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);



        TextView eateryName = myDialog.findViewById(R.id.eatery_name);
        TextView eateryLoc = myDialog.findViewById(R.id.eatery_addresss);
        TextView eateryTime = myDialog.findViewById(R.id.eatery_op_time);
        TextView delivererCount = myDialog.findViewById(R.id.eatery_details_num_deliverers);
        eateryName.setText(e.getEateryName());
        eateryLoc.setText(e.getEateryAddress() + ", " + e.getEateryStreet());
        eateryTime.setText(e.getOperatingTime());
        DelivererOfferMgr.getEateryDeliverers(e).get()
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
                                if (!cutoffDT.isBefore(now)){
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

    private void getCurrentLocation() {
        //initialize task location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null | supportMapFragment != null) {
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.eatery_selection_map);
                    mapFragment.getMapAsync(new OnMapReadyCallback(){

                        @Override
                        public void onMapReady(GoogleMap googleMap) {
//                            LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
                            LatLng latlng = new LatLng(1.3450, 103.9832);
                            MarkerOptions options = new MarkerOptions().position(latlng).title("Current Location");

                            //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 18f));

                            googleMap.addMarker(options);
                        }

                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //when permission granted, call method
                getCurrentLocation();
            }
        }

    }


    @Override
    protected void onStart()
    {
        super.onStart();
        i = getIntent();

        Button dummySearchBtn = (Button) findViewById(R.id.dummy_search);

        dummySearchBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                onClickSearch(v.getContext());
            }
        });
    }



    public Intent getI() {
        return i;
    }

    //FOR LOGOUT BUTTON ON MAPS HOMEPAGE
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void logout() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.logout)
                .setMessage(R.string.are_you_sure)
                .setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mLogoutPresenter.logout();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onLogoutSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        LoginActivity.startIntent(this,
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    public void onLogoutFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}