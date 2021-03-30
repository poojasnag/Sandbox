package com.sandbox.chat.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.libraries.maps.CameraUpdateFactory;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.LatLng;
import com.google.android.libraries.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.eatery_selection_map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
        locationDetails = new Dialog(this);
    }


    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

//        add individual markings
        LatLng jp = new LatLng(1.3399373652678745, 103.7067511545603);
        mMap.addMarker(new MarkerOptions().position(jp).title("Marker in JP"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jp, 18f));

        //import geojson file into map
        try {
            GeoJsonLayer layer = new GeoJsonLayer(mMap, R.raw.raw, this);
            GeoJsonPolygonStyle polyStyle = layer.getDefaultPolygonStyle();
            polyStyle.setStrokeColor(Color.CYAN);
            polyStyle.setStrokeWidth(2);
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