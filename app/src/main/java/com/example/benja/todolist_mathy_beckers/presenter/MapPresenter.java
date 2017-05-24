package com.example.benja.todolist_mathy_beckers.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.benja.todolist_mathy_beckers.model.MapManager;
import com.google.android.gms.maps.MapFragment;

/**
 * Created by Benja on 23-05-17.
 */
public class MapPresenter implements IMapPresenter {
    MapManager mapManager;
    Activity activity;

    public MapPresenter(Activity activity){
        this.activity = activity;
    }

    @Override
    public void initializeMap(MapFragment fragment) {
        mapManager= new MapManager(activity);
        mapManager.InitializeMap(fragment);
    }

    @Override
    public void validatePosition() {
        mapManager.createGeofences();
    }

    @Override
    public void onStart() {
        mapManager.connectGoogleApiClient();
    }

    @Override
    public void onStop() {
        mapManager.disconectGoogleApiClient();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mapManager.onConnected(bundle);
    }

}
