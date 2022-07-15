package com.iot.minkyu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iot.minkyu.databinding.ActivityMapBinding;

    public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

        private GoogleMap mMap;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_map);

            Button button_to_main = findViewById(R.id.button_to_main);

            button_to_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MapActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }


        @Override
        public void onMapReady(final GoogleMap googleMap) {
            mMap = googleMap;
            LatLng sydney = new LatLng(-33.852, 151.211);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            LatLng seoul = new LatLng(37.564214, 127.001699);
            googleMap.addMarker(new MarkerOptions().position(seoul).title("Marker in Seoul"));
            LatLng dejeonuniv = new LatLng(36.336294, 127.458212);
            googleMap.addMarker(new MarkerOptions().position(dejeonuniv).title("Marker in DejeonUniv"));
            LatLng dejeonstation = new LatLng(36.332316, 127.434191);
            googleMap.addMarker(new MarkerOptions().position(dejeonstation).title("Marker in DejeonStation"));
        }
    }