package com.example.publictransport;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

public class DestinationActivity extends AppCompatActivity {

    MapSetup mapSetup;
    LatLng sourceLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        sourceLatLng = i.getParcelableExtra("EXTRA_SOURCE_LOCATION");
        Log.i("DestinationActivity","Source location: " + sourceLatLng);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.setTitle("Destination");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.autocomplete_fragment);

        mapSetup = new MapSetup(this, mapFragment, autocompleteFragment);

        mapSetup.getLastLocation();

        saveLocation();
    }

    public void saveLocation(){
        Button saveLocationButton = findViewById(R.id.save_location_btn);
        saveLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("SourceActivity", "mapSetup.getSourceOrDestLocation()" + mapSetup.getSourceOrDestLocation());
                LatLng sourceOrDestLocation = mapSetup.getSourceOrDestLocation();

                if(mapSetup.getSourceOrDestLocation() != null) {
                    Bundle args = new Bundle();
                    args.putParcelable("destinationLocation", sourceOrDestLocation);
                    args.putParcelable("sourceLocation", sourceLatLng);
                    Intent intent = new Intent(DestinationActivity.this, PathsCardsActivity.class);
                    intent.putExtras(args);
                    //ActivityCompat.startActivityForResult(DestinationActivity.this, intent, 0 , null);
                    startActivity(intent);
                }
            }
        });
    }
}
