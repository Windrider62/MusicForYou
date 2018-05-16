package com.company;

import java.net.HttpURLConnection;
import java.net.URL;


public class HttpControls {
    public static  String HttpGetMethod(String requestPath) throws Exception{

        URL url = new URL(requestPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        System.out.println(conn.getResponseMessage());
        System.out.println("end method");
        return conn.getResponseMessage();

    }
}
