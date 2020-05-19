package com.example.wifi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;


public class MainActivity extends AppCompatActivity {

    private Button button;
    TextView textView;

    private Button location;
    //HTTP server;
    protected GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.makeDelivText);
        button = (Button) findViewById(R.id.manuallyBtn);
        location = (Button) findViewById(R.id.currentLocation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {

                RequestHelper.requestToServer("");
                //Toast.makeText(getApplicationContext(), "Response code "+ String.valueOf(requestStatus) ,Toast.LENGTH_SHORT).show();
                //server.request(Cars.magess.getUrl().toString());
                //
                    openActivitySecond();
//                }else{
//                    Toast.makeText(getApplicationContext(),"Not connected",Toast.LENGTH_SHORT).show();
//                }
            }
        });

        location.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v) {
                openMapActivity();

            }

        }
        );


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
