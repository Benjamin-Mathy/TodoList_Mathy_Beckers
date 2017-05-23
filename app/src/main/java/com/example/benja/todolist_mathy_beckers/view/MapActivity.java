package com.example.benja.todolist_mathy_beckers.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.benja.todolist_mathy_beckers.R;
import com.example.benja.todolist_mathy_beckers.presenter.IMapPresenter;
import com.example.benja.todolist_mathy_beckers.presenter.MapPresenter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.MapFragment;

/**
 * Created by Benja on 18-04-17.
 */
public class MapActivity extends AppCompatActivity implements IMapActivity,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private IMapPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        presenter = new MapPresenter(this);


        presenter.initializeMap((MapFragment) getFragmentManager().findFragmentById(R.id.map));
    }
    protected void onStart() {
        presenter.onStart();
        super.onStart();
    }

    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }
    public void validateLocation(View view){
        presenter.validatePosition();
        Toast.makeText(this.getBaseContext(), "Location validate", Toast.LENGTH_SHORT).show();
        this.finish();
    }


    public void cancelLocation(View view){
        this.finish();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        presenter.onConnected(bundle);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
