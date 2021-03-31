package com.sandbox.chat.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.maps.CameraUpdateFactory;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.LatLng;
import com.google.android.libraries.maps.model.MarkerOptions;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sandbox.chat.models.Deliverer;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Layer;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.sandbox.chat.ui.BottomBarOnClickListener;
import com.sandbox.chat.R;
import com.sandbox.chat.mgr.EaterySelectionMapMgr;

import org.json.JSONException;

import java.io.IOException;

/**
 * Displays the eatery selection interface
 */
public class EaterySelectionMapActivity extends AppCompatActivity implements OnMapReadyCallback{
    EaterySelectionMapMgr eaterySelectionMapController;
    public Intent i;
    private GoogleMap mMap;
    private GeoJsonLayer layer1;

    FusedLocationProviderClient client;
    SupportMapFragment supportMapFragment;

    public Dialog getLocationDetails() {
        return locationDetails;
    }

    Dialog locationDetails;
    /**
     * Initialize the interface
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = LocationServices.getFusedLocationProviderClient(this);


        setContentView(R.layout.activity_eatery_selection_map);
        try {
            eaterySelectionMapController = new EaterySelectionMapMgr(this);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        final BottomNavigationView bot_bar = findViewById(R.id.eatery_selection_botnav);
        bot_bar.setOnNavigationItemSelectedListener(new BottomBarOnClickListener(bot_bar));

        locationDetails = new Dialog(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.eatery_selection_map);
        mapFragment.getMapAsync(this);

        //initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this);

        //check permission
        if (ActivityCompat.checkSelfPermission(EaterySelectionMapActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }
        else {
            //when permission denied, request permission
            ActivityCompat.requestPermissions(EaterySelectionMapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

//        if (getIntent().getSerializableExtra("user") instanceof Deliverer){
//            Toast.makeText(EaterySelectionMapActivity.this, "Is deliverer object: ", Toast.LENGTH_SHORT).show();
//        }
//        else{
////            Toast.makeText(EaterySelectionMapActivity.this, "Is not deliv object: " + getIntent().getSerializableExtra("user").getClass().getName(), Toast.LENGTH_SHORT).show();
//        }

        //initialize fused location

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
                            System.out.println("Found user's location");
                            LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latlng).title("Current Location");
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

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);


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
                    eaterySelectionMapController.showLocationDetails(feature.getProperty("Name"));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        i = getIntent();
    }



    public Intent getI() {
        return i;
    }
}