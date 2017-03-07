package com.olerom.formula.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Date: 08.03.17
 *
 * @author olerom
 */
public class ErgastConnection {

    private final String USER_AGENT = "Mozilla/5.0";


    public void configureRequest() {
        try {
            sendGetRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendGetRequest() throws Exception {
        String url = "http://ergast.com/api/f1/2016/1/results.json";

        URL obj = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();

        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = httpURLConnection.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        System.out.println(response.toString());
    }
}
