package com.example.benja.todolist_mathy_beckers.model;

import android.app.Activity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


/**
 * Created by Youba on 08/05/2017.
 */

public class LocationManager  {
    private Activity activity;
    private GoogleApiClient mGoogleApiClient;

    public LocationManager (Activity activity){
        this.activity = activity;


    }
}
