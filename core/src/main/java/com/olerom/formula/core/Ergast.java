package com.olerom.formula.core;

import com.olerom.formula.core.objects.Circuit;
import com.olerom.formula.core.objects.Constructor;
import com.olerom.formula.core.objects.Driver;
import com.olerom.formula.core.objects.Season;
import com.olerom.formula.core.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Date: 08.03.17
 *
 * @author olerom
 */
public class Ergast {

    private final static String USER_AGENT = "Mozilla/5.0";
    private final static String DRIVERS_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/drivers.json?limit={LIMIT}&offset={OFFSET}";
    private final static String CIRCUITS_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/circuits.json?limit={LIMIT}&offset={OFFSET}";
    private final static String CONSTRUCTORS_REQ = "http://ergast.com/api/{SERIES}/constructors.json?limit={LIMIT}&offset={OFFSET}";
    private final static String SEASONS_REQ = "http://ergast.com/api/{SERIES}/seasons.json?limit={LIMIT}&offset={OFFSET}";

    public List<Driver> getDrivers(int season, int limit, int offset) throws IOException {
        String url = DRIVERS_REQ.
                replace("{SERIES}", "f1").
                replace("{SEASON}", String.valueOf(season)).
                replace("{LIMIT}", String.valueOf(limit)).
                replace("{OFFSET}", String.valueOf(offset));
        String json = getJson(url);
        return new Parser().parse(json, "DriverTable", "Drivers", Driver.class);
    }

    public List<Circuit> getCircuits(int season, int limit, int offset) throws IOException {
        String url = CIRCUITS_REQ.
                replace("{SERIES}", "f1").
                replace("{SEASON}", String.valueOf(season)).
                replace("{LIMIT}", String.valueOf(limit)).
                replace("{OFFSET}", String.valueOf(offset));
        String json = getJson(url).replaceAll("long", "lng");
        return new Parser().parse(json, "CircuitTable", "Circuits", Circuit.class);
    }

    public List<Season> getSeasons(int limit, int offset) throws IOException {
        String url = SEASONS_REQ.
                replace("{SERIES}", "f1").
                replace("{LIMIT}", String.valueOf(limit)).
                replace("{OFFSET}", String.valueOf(offset));
        ;
        String json = getJson(url);
        return new Parser().parse(json, "SeasonTable", "Seasons", Season.class);
    }

    public List<Constructor> getConstructors(int limit, int offset) throws IOException {
        String url = CONSTRUCTORS_REQ.
                replace("{SERIES}", "f1").
                replace("{LIMIT}", String.valueOf(limit)).
                replace("{OFFSET}", String.valueOf(offset));
        ;
        String json = getJson(url);
        return new Parser().parse(json, "ConstructorTable", "Constructors", Constructor.class);
    }


    private String getJson(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();

        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
