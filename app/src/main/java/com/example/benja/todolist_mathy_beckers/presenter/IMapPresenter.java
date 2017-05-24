package com.example.benja.todolist_mathy_beckers.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.maps.MapFragment;

/**
 * Created by Benja on 23-05-17.
 */
public interface IMapPresenter {

    void initializeMap(MapFragment fragment);
    void validatePosition();
    void onStart();
    void onStop();
    void onConnected(@Nullable Bundle bundle);
}
