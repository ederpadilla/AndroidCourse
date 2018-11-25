package dev.eder.androidcourse.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;


import dev.eder.androidcourse.R;
import dev.eder.androidcourse.Util.KeysConstants;

public class LocationActivity extends AppCompatActivity  implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    FusedLocationProviderClient mFusedLocationClient;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar2);
        textView = findViewById(R.id.tv_location);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        buildGoogleApiClient();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.reconnect();
    }

    @Override
    public void onResume() {
        super.onResume();
        int response = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(LocationActivity.this);
        if (response != ConnectionResult.SUCCESS) {
            Log.d("MainActivity", "Google Play Service Not Available");
            GoogleApiAvailability.getInstance().getErrorDialog(LocationActivity.this, response, 1).show();
        } else {
            if (mGoogleApiClient != null && mFusedLocationClient != null) {
                requestLocationUpdates();
            } else {
                buildGoogleApiClient();
            }
            Log.d("MainActivity", "Google play service available");
        }
    }

    public void requestLocationUpdates() {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(2000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    KeysConstants.LOCATION_PERMISSION_ID);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    LocationCallback mLocationCallback = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                Log.w("MainActivity", "🌮🌮🌮🌮Location: " + location.getLatitude() + " " + location.getLongitude());
                textView.setText(getEmojiByUnicode(0x1F680)+getEmojiByUnicode(0x1F680)+"Location: " + location.getLatitude()  + location.getLongitude());
                progressBar.setVisibility(View.GONE);

            }
        }
    };

    @Override
    public void onConnected(Bundle bundle) {
        Log.w("MainActivity", "🌮🌮🌮🌮onConnected ");
        requestLocationUpdates();
    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.w("MainActivity", "🌮🌮🌮🌮onConnectionSuspended ");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w("MainActivity", "🌮🌮🌮🌮onConnectionFailed ");

    }
    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case KeysConstants.LOCATION_PERMISSION_ID: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        onResume();
                    }

                } else {

                    showPopUp();

                }
                return;
            }

        }
    }

    private void showPopUp() {

    }
}
