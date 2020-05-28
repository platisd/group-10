package com.example.wifi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button button;
    TextView textView;

    private Button location;
    private Button automatic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonInitialiser();

        button.setOnClickListener(v -> openActivitySecond());

        automatic.setOnClickListener(v ->
                RequestHelper.requestToServer("/G"));

        location.setOnClickListener(v -> openMapActivity()
        );


    }

    private void buttonInitialiser() {
        textView = findViewById(R.id.makeDelivText);
        button   = findViewById(R.id.manuallyBtn);
        location = findViewById(R.id.currentLocation);
        automatic = findViewById(R.id.manuallyBtn2);
    }

    public void openActivitySecond() {
        Intent intent = new Intent(MainActivity.this, ManualControlActivity.class);
        startActivity(intent);

    }

    public void openMapActivity (){
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }




}
