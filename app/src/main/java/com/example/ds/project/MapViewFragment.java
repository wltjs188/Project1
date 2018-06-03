package com.example.ds.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay.OnStateChangeListener;

public class MapViewFragment extends NMapFragment implements NMapView.OnMapStateChangeListener, NMapPOIdataOverlay.OnStateChangeListener {
    NMapView mapView;
    NMapController mapController;
    NMapViewerResourceProvider mapViewerResourceProvider;
    NMapOverlayManager mapOverlayManager;
    NMapPOIdata poidata;
    int markerId = NMapPOIflagType.PIN;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    double longitude, latitude;
    String name;
    int id;
    RestItem rest = new RestItem();
    RestInfoActivity restInfoActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_view_fragment, container, false);
        mapView = (NMapView) v.findViewById(R.id.map_view);
        mapView.setClientId("SRQ8fQ4AjnbYFQuTXNDu");
        mapView.setClickable(true);
        //database = FirebaseDatabase.getInstance();
        //databaseReference = database.getReference();
        //id = getArguments().getInt("menuId");

        return v;

    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.setBuiltInZoomControls(true, null);
        mapView.setOnMapStateChangeListener(this);
        
        mapController = mapView.getMapController();
        mapViewerResourceProvider = new NMapViewerResourceProvider(getActivity());
        mapOverlayManager = new NMapOverlayManager(getActivity(), mapView, mapViewerResourceProvider);
        restInfoActivity = (RestInfoActivity) getActivity();

        longitude = restInfoActivity.longitude;
        latitude = restInfoActivity.latitude;
        name = restInfoActivity.name;
        moveMapCenter();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
        if (nMapError == null) {
            moveMapCenter();
        } else {
            Log.e("map init error", nMapError.message);
        }
        
    }

    private void moveMapCenter() {

        NGeoPoint currentPoint = new NGeoPoint(longitude, latitude);
        mapController.setMapCenter(currentPoint);

        poidata = new NMapPOIdata(1, mapViewerResourceProvider);
        poidata.beginPOIdata(1);
        poidata.addPOIitem(longitude, latitude, name, markerId, 0);
        poidata.endPOIdata();

        NMapPOIdataOverlay poiDataOverlay = mapOverlayManager.createPOIdataOverlay(poidata, null);
        
    }

    @Override
    public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

    }

    @Override
    public void onMapCenterChangeFine(NMapView nMapView) {

    }

    @Override
    public void onZoomLevelChange(NMapView nMapView, int i) {

    }

    @Override
    public void onAnimationStateChange(NMapView nMapView, int i, int i1) {

    }

    @Override
    public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

    }

    @Override
    public void onCalloutClick(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

    }
}
