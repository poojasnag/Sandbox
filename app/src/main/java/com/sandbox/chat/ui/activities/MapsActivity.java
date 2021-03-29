package com.sandbox.chat.ui.activities;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.libraries.maps.CameraUpdateFactory;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.LatLng;
import com.google.android.libraries.maps.model.MarkerOptions;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.sandbox.chat.R;

import android.graphics.Color;

//import com.google.maps.*;
//import com.google.android.libraries.maps.GoogleMap;

import org.json.JSONException;

import java.io.IOException;

@Deprecated
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GeoJsonLayer layer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        LatLng jp = new LatLng(1.3399373652678745, 103.7067511545603);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jp, 18f));

    }
}