package com.example.publictransport;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.GeoPoint;

public class PathsCardsActivity extends AppCompatActivity {

    ListView listView;
    ProgressBar progressBar;
    String TAG = "PathsCardsActivity";
    PlanJourney journey ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paths_cards);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle("Possible paths");

        listView = findViewById(R.id.list);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        readObject();
    }

    public void readObject() {
        LatLng sourceLocationLatLng = getIntent().getExtras().getParcelable("sourceLocation");
        GeoPoint sourceLocation = new GeoPoint(sourceLocationLatLng.latitude, sourceLocationLatLng.longitude);

        LatLng destinationLocationLatLng = getIntent().getExtras().getParcelable("destinationLocation");
        GeoPoint destinationLocation = new GeoPoint(destinationLocationLatLng.latitude, destinationLocationLatLng.longitude);

        journey = new PlanJourney(sourceLocation, destinationLocation, this);
        journey.readDataFromFirestore();
    }
}
