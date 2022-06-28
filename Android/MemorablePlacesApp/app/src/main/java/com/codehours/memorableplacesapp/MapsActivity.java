package com.codehours.memorableplacesapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    LocationManager locationManager;
    LocationListener locationListener;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 1);

        if (index > 0) {
            Location location = new Location(LocationManager.GPS_PROVIDER);
            location.setLatitude(MainActivity.latLangs.get(index - 1).latitude);
            location.setLongitude(MainActivity.latLangs.get(index - 1).longitude);
            updateMapMarker(location, MainActivity.places.get(index));
        } else {
            // one long press save location into memory
            mMap.setOnMapLongClickListener(latLng -> {
                try {
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    List<Address> addressDetails = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

                    Log.i("AddressDetails", addressDetails.toString());

                    String featureName = addressDetails.get(0).getAddressLine(0);
                    String[] addressLine = featureName.split(", ");
                    if (addressLine.length > 2) {
                        int i = 0;
                        featureName = "";
                        for (i = 0; i < 2; i++) {
                            featureName += addressLine[i] + ", ";
                        }
                        featureName += addressLine[i];
                    }

                    mMap.addMarker(new MarkerOptions().position(latLng).title(featureName).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                    MainActivity.places.add(featureName);
                    MainActivity.latLangs.add(latLng);
                    MainActivity.adapter.notifyDataSetChanged();

                    Toast.makeText(this, "Location Added", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "ERROR: Try Again", Toast.LENGTH_SHORT).show();
                }
            });

            locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    updateMapMarker(location, "Me");
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
            showResult();
        }
    }


    // method will show the users last location
    private void showResult() {
        if (checkPermission()) {
            if (isLocationEnabled()) {
                if (isDataEnabled()) {
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        updateMapMarker(location, "Me");
                    } else {
                        requestNewLocationData();
                    }
                } else {
                    Toast.makeText(this, "turn on mobile data", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, "turn on the GPS", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    // method will update marker live
    private void updateMapMarker(Location location, String title) {
        mMap.clear();
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (new Intent().getIntExtra("index", 0) == 0) {
            mMap.addMarker(new MarkerOptions().position(latLng).title(title));
        } else {
            mMap.addMarker(new MarkerOptions().position(latLng).title(title).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
    }

    // method will request permission from the user
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    // method will call requestNewLocationData
    private void requestNewLocationData() {
        if (checkPermission()) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    // method will check if permission granted
    private boolean checkPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    // method will return true if location is enabled
    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    // method will return true if wifi or data is enabled
    private boolean isDataEnabled() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null;
    }

    // override method to call requestNewLocationData when permissions granted
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            requestNewLocationData();
        }
    }
}