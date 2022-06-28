package com.codehours.hikerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    LocationListener locationListener;

    TextView latitudeValue;
    TextView longitudeValue;
    TextView accuracyValue;
    TextView altitudeValue;
    TextView addressValue;

    private void showLastResult() {
        if (checkPermission()) {
            if (isLocationEnabled()) {
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                requestNewLocationData();
                if (location != null) {
                    showLocationData(location);
                }
            } else {
                Toast.makeText(this, "turn on your GPS", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    private void showLocationData(Location location) {
        latitudeValue.setText(String.format(Locale.getDefault(), "%.2f", location.getLatitude()));
        longitudeValue.setText(String.format(Locale.getDefault(), "%.2f", location.getLongitude()));
        accuracyValue.setText(String.valueOf(location.getAccuracy()));
        altitudeValue.setText(String.valueOf(location.getAltitude()));

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            //store the details in a list
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (!addresses.isEmpty()) {
                String address = String.format(Locale.getDefault(), "%s, %s", addresses.get(0).getLocality(), addresses.get(0).getSubAdminArea());
                addressValue.setText(address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    private void requestNewLocationData() {
        if (checkPermission()) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    private boolean checkPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitudeValue = findViewById(R.id.latitudeValue);
        longitudeValue = findViewById(R.id.longitudeValue);
        accuracyValue = findViewById(R.id.accuracyValue);
        altitudeValue = findViewById(R.id.altitudeValue);
        addressValue = findViewById(R.id.addressValue);

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                showLocationData(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }
        };

        showLastResult();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            requestNewLocationData();
        }
    }
}