package com.example.benja.todolist_mathy_beckers.model;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Benja on 23-05-17.
 */

public class MapManager implements OnMapReadyCallback, LocationListener {
    private Activity activity;

    private List<Geofence> mGeofenceList;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Marker marker;
    public GoogleMap map;

    public MapManager(Activity activity){
        this.activity=activity;
    }

    public void InitializeMap(MapFragment mapFragment){

        mapFragment.getMapAsync(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(activity)
                    .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) activity)
                    .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) activity)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGeofenceList = new ArrayList<>();
    }

    @Override
    public void onMapReady(GoogleMap map){
        this.map=map;
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            this.map.setMyLocationEnabled(true);
        }
        this.map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                mapClicked(point);
            }
        });
    }

    public void mapClicked(LatLng location){
        this.map.clear();
        if(location != null) {
            marker = this.map.addMarker(new MarkerOptions()
                    .position(new LatLng(location.latitude, location.longitude))
                    .title("Marker"));
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation=location;
    }

    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLastLocation != null) {

            marker= this.map.addMarker(new MarkerOptions()
                    .position(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()))
                    .title("Marker"));
        }
    }

    public void connectGoogleApiClient(){
        mGoogleApiClient.connect();
    }

    public void disconectGoogleApiClient(){
        mGoogleApiClient.disconnect();
    }

    public void createGeofences() {
        String id = UUID.randomUUID().toString();
        Geofence fence = new Geofence.Builder()
                .setRequestId(id)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .setCircularRegion(marker.getPosition().latitude, marker.getPosition().longitude, 200) // Try changing your radius
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build();
        mGeofenceList.add(fence);
    }

}
