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

    private final static String USER_AGENT = "Mozilla/5.0";
    public final static String DRIVER = "drivers";

    public void configureRequest() {
        try {
            String url1 = "http://ergast.com/api/f1/2010/circuits.json";
            String url2 = "http://ergast.com/api/f1/2017/drivers.json";
            sendGetRequest(url1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void sendGetRequest(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();

        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

//        new Parser().parseDriver(response.toString());
        new Parser().parseCircuit(response.toString());
    }
}
