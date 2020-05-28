package com.example.wifi;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class MapCoordinatesRequest {


    String request(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String responseBody;
        Request request = new Request.Builder().url(url).build();

        try {
            //set time in mili
            Thread.sleep(7000);

        }catch (Exception e){
            e.printStackTrace();
        }

        try (Response response = client.newCall(request).execute()) {
            responseBody = Objects.requireNonNull(response.body()).string();
            // ... do something with response
        }
        return responseBody;
    }

}
