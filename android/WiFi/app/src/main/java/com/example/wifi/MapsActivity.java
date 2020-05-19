package com.example.wifi;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
        {

    private static final String LOG_TAG = "ExampleApp";

    private static final String SERVICE_URL = "http://www.mocky.io/v2/5ec46ca9300000b46939c881";

    private GoogleMap map;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//         SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//               .findFragmentById(R.id.map);
//         mapFragment.getMapAsync(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        if (map == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFrag.getMapAsync( this);
            if (map != null) {
                setUpMap();
            }
        }
    }


    private void setUpMap() {
        // Retrieve the city data from the web service
        // In a worker thread since it's a network operation.
        new Thread(new Runnable() {
            public void run() {
                try {
                    retrieveAndAddCities();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Cannot retreive cities", e);
                    return;
                }
            }
        }).start();
    }


    protected void retrieveAndAddCities() throws IOException {
        HttpURLConnection conn = null;
        final StringBuilder json = new StringBuilder();
        try {
            // Connect to the web service
            URL url = new URL(SERVICE_URL);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Read the data into the StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                json.append(buff, 0, read);
                //System.out.println("My values are changing "+ json);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to service", e);
            throw new IOException("Error connecting to service", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

         //Create markers for the city data.
        // Must run this on the UI thread since it's a UI operation.
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    //System.out.println("My values are "+ json.toString());
                    createMarkersFromJson(json.toString());
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "Error processing JSON", e);
                }
            }
        });
    }



    void createMarkersFromJson(String json) throws JSONException {
    
        JSONObject jsonObj = new JSONObject();
        String []array = json.split(",");
        //System.out.println("My first value "+ array[0]);
        //System.out.println("My second value "+ array[1]);
        jsonObj.put("lat", array[0]);
        jsonObj.put("lng", array[1]);
        //System.out.println("My Objects are "+ jsonObj);
        LatLng some = new LatLng(57.706478,11.9374733);
        //LatLng magess = new LatLng(jsonObj.getDouble("lat"),jsonObj.getDouble("lng"));
        //Print statements were mainly used for the Json
        // But gonna leave it here as comments for later activities.
        //System.out.println("My whaaat 1 "+ jsonObj.getDouble("lat"));
        //System.out.println("My whaaat 2 "+ jsonObj.getDouble("lng"));
        map.addMarker(new MarkerOptions().position(some).title("Marker of Magess"));
        map.moveCamera(CameraUpdateFactory.newLatLng(some));

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    //@Override


    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        setUpMap();
        
        /* The codes below were generated automatically
        so Just leaving it there 
        */

        // Add a marker in Sydney and move the camera

//        LatLng sydney = new LatLng(57.706478,11.9374733);
//        map.addMarker(new MarkerOptions().position(sydney).title("Marker of Magess"));
//        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}

