package com.horizonlabs.rebelfoods.ui.activities;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.horizonlabs.rebelfoods.R;
import com.horizonlabs.rebelfoods.ui.base.BaseActivity;
import com.horizonlabs.rebelfoods.utils.Constants;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double lat = 0, lon = 0;
    TextView tvAddress;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvAddress = findViewById(R.id.tvAddress);
        if (getIntent().hasExtra(Constants.LAT) && getIntent().hasExtra(Constants.LON)) {

            try {
                lat = Double.parseDouble(getIntent().getStringExtra(Constants.LAT));
                lon = Double.parseDouble(getIntent().getStringExtra(Constants.LON));
                address = getIntent().getStringExtra(Constants.ADDRESS);
                tvAddress.setText(address);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        LatLng latLng = new LatLng(lat, lon);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                latLng).zoom(12).build();

        mMap.addMarker(new MarkerOptions().position(latLng).title("Lat " + lat + " Lon " + lon));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
