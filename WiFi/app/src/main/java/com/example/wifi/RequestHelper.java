package com.example.wifi;

import java.net.MalformedURLException;
import java.util.ArrayList;


public class RequestHelper {

    //private static ArrayList<Magess> magessCars= new ArrayList<>();
    //private static Magess magess;
    public static Magess magess;
    static HTTP server = new HTTP();

    static {
        try {
            magess = new Magess("http://192.168.0.5/", "Magess");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void requestToServer(String s) {
//        if (s == "") {
//            server.request(magess.getUrl().toString() + "F");
//        } else {
            server.request(magess.getUrl().toString() + s);
        //}
    }

}