package com.example.wifi;

import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HTTP {

    //int flag = 100;

    public void request(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //System.out.println("My Url is " + url);
                //Log.i("My URL is ", url);
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    Log.d("AA", "resp [" + response.body().string() + "]");
                }
                System.out.println("My response is " + response.code());


            }

        });

    }



}