package com.example.publictransport;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.imperiumlabs.geofirestore.GeoFirestore;
import org.imperiumlabs.geofirestore.GeoQuery;
import org.imperiumlabs.geofirestore.listeners.GeoQueryDataEventListener;

import java.util.ArrayList;
import java.util.List;

public class PlanJourney {

    //private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("lines/al-siteen");
    private GeoPoint mSourceLocation;
    private GeoPoint mDestinationLocation;
    private String TAG = "PlanJourney";
    private Context mContext;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference mCollRef = FirebaseFirestore.getInstance().collection("stops");
    private GeoFirestore geoFirestore = new GeoFirestore(mCollRef);
    private GeoQuery geoQuery;

    public PlanJourney(GeoPoint startLocation, GeoPoint endLocation, Context context) {
        mSourceLocation = startLocation;
        mDestinationLocation = endLocation;
        mContext = context;
    }

    public void readDataFromFirestore() {
        /*if (mContext instanceof Activity) {
            Activity activity = (Activity) mContext;*/
//        geoFirestore.setLocation("siteen_stop", new GeoPoint(15.536355, 32.583614));
//        geoFirestore.setLocation("Buri_bridge_stop", new GeoPoint(15.609263, 32.553832));
        //geoFirestore.setLocation("Al-Mashtal_stop", new GeoPoint(15.579637, 32.560889));
        geoQuery = geoFirestore.queryAtLocation(mSourceLocation, 0.5);
        geoQuery.addGeoQueryDataEventListener(new GeoQueryDataEventListener() {
            @Override
            public void onDocumentEntered(DocumentSnapshot documentSnapshot, GeoPoint geoPoint) {
                Log.d(TAG, "stop data: " +documentSnapshot.getId() + " " + documentSnapshot.getData());
                db.collection("lines").whereEqualTo("departure_stop_id", documentSnapshot.getId()).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }

                        List<String> linesList = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            linesList.add(doc.getId());
                        }
                        Log.d(TAG, "lines " + linesList);
                        
                    }
                });
            }

            @Override
            public void onDocumentExited(DocumentSnapshot documentSnapshot) {

            }

            @Override
            public void onDocumentMoved(DocumentSnapshot documentSnapshot, GeoPoint geoPoint) {

            }

            @Override
            public void onDocumentChanged(DocumentSnapshot documentSnapshot, GeoPoint geoPoint) {

            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(Exception e) {

            }
        });
    }


        /*public void removeListeners(){
            geoQuery.removeAllListeners();
        }*/
    }
