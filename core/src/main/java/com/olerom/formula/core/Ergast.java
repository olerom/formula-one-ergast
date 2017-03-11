package com.olerom.formula.core;

import com.olerom.formula.core.objects.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.olerom.formula.core.parser.Parser.parse;

/**
 * Date: 08.03.17
 *
 * @author olerom
 */
public class Ergast {

    private final static String USER_AGENT = "Mozilla/5.0";
    private final static String DRIVERS_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/drivers.json?limit={LIMIT}&offset={OFFSET}";
    private final static String CIRCUITS_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/circuits.json?limit={LIMIT}&offset={OFFSET}";
    private final static String CONSTRUCTORS_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/constructors.json?limit={LIMIT}&offset={OFFSET}";
    private final static String SEASONS_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/seasons.json?limit={LIMIT}&offset={OFFSET}";
    private final static String SCHEDULE_REQ = "http://ergast.com/api/{SERIES}/{SEASON}.json?limit={LIMIT}&offset={OFFSET}";
    private final static String RESULTS_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/{ROUND}/results.json";
    private final static String QUALIFYING_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/{ROUND}/qualifying.json";
    private final static String DRIVER_STANDINGS_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/{ROUND}/driverStandings.json";

    /**
     * @param season is a season which you want to get, (-1) if you want to get all the seasons.
     * @param limit  is a number of results that are returned. up to a maximum value of 1000.
     *               Please use the smallest value that your application needs. If (-1), the default value is 30.
     * @param offset specifies an offset into the result set. If (-1), the default value is 30.
     * @return list of drivers that satisfy your query.
     */
    public List<Driver> getDrivers(int season, int limit, int offset) throws IOException {
        String url = getUrl(DRIVERS_REQ, season, limit, offset);
        String json = getJson(url);
        return parse(json, new String[]{"DriverTable", "Drivers"}, Driver.class);
    }

    /**
     * @param season is a season which you want to get, (-1) if you want to get all the seasons.
     * @param limit  is a number of results that are returned. up to a maximum value of 1000.
     *               Please use the smallest value that your application needs. If (-1), the default value is 30.
     * @param offset specifies an offset into the result set. If (-1), the default value is 30.
     * @return list of circuits that satisfy your query.
     */
    public List<Circuit> getCircuits(int season, int limit, int offset) throws IOException {
        String url = getUrl(CIRCUITS_REQ, season, limit, offset);
        String json = getJson(url).replace("long", "lng");
        return parse(json, new String[]{"CircuitTable", "Circuits"}, Circuit.class);
    }

    /**
     * @param season is a season which you want to get, (-1) if you want to get all the seasons.
     * @param limit  is a number of results that are returned. up to a maximum value of 1000.
     *               Please use the smallest value that your application needs. If (-1), the default value is 30.
     * @param offset specifies an offset into the result set. If (-1), the default value is 30.
     * @return list of seasons that satisfy your query.
     */
    public List<Season> getSeasons(int season, int limit, int offset) throws IOException {
        String url = getUrl(SEASONS_REQ, season, limit, offset);
        String json = getJson(url);
        return parse(json, new String[]{"SeasonTable", "Seasons"}, Season.class);
    }

    /**
     * @param season is a season which you want to get, (-1) if you want to get all the seasons.
     * @param limit  is a number of results that are returned. up to a maximum value of 1000.
     *               Please use the smallest value that your application needs. If (-1), the default value is 30.
     * @param offset specifies an offset into the result set. If (-1), the default value is 30.
     * @return list of constructors that satisfy your query.
     */
    public List<Constructor> getConstructors(int season, int limit, int offset) throws IOException {
        String url = getUrl(CONSTRUCTORS_REQ, season, limit, offset);
        String json = getJson(url);
        return parse(json, new String[]{"ConstructorTable", "Constructors"}, Constructor.class);
    }

    /**
     * @param season is a season which you want to get, (-1) if you want to get all the seasons.
     * @param limit  is a number of results that are returned. up to a maximum value of 1000.
     *               Please use the smallest value that your application needs. If (-1), the default value is 30.
     * @param offset specifies an offset into the result set. If (-1), the default value is 30.
     * @return list of schedules that satisfy your query.
     */
    public List<Schedule> getSchedules(int season, int limit, int offset) throws IOException {
        String url = getUrl(SCHEDULE_REQ, season, limit, offset);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races"}, Schedule.class);
    }

    /**
     * @param season is a season which you want to get, (-1) if you want to get all the seasons.
     * @param round  is a round which you want to get.
     * @param limit  is a number of results that are returned. up to a maximum value of 1000.
     *               Please use the smallest value that your application needs. If (-1), the default value is 30.
     * @param offset specifies an offset into the result set. If (-1), the default value is 30.
     * @return list of race results that satisfy your query.
     */
    public List<RaceResult> getRaceResults(int season, int round, int limit, int offset) throws IOException {
        String url = getUrl(RESULTS_REQ, season, limit, offset);
        url = getResultsUrl(url, round);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races", "Results"}, RaceResult.class);
    }

    /**
     * @param season is a season which you want to get, (-1) if you want to get all the seasons.
     * @param round  is a round which you want to get.
     * @param limit  is a number of results that are returned. up to a maximum value of 1000.
     *               Please use the smallest value that your application needs. If (-1), the default value is 30.
     * @param offset specifies an offset into the result set. If (-1), the default value is 30.
     * @return list of qualification results that satisfy your query.
     */
    public List<Qualification> getQualificationResults(int season, int round, int limit, int offset) throws IOException {
        String url = getUrl(QUALIFYING_REQ, season, limit, offset);
        url = getResultsUrl(url, round);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races", "QualifyingResults"}, Qualification.class);
    }

    /**
     * @param season is a season which you want to get, (-1) if you want to get all the seasons.
     * @param round  is a round which you want to get.
     * @param limit  is a number of results that are returned. up to a maximum value of 1000.
     *               Please use the smallest value that your application needs. If (-1), the default value is 30.
     * @param offset specifies an offset into the result set. If (-1), the default value is 30.
     * @return list of driver standings that satisfy your query.
     */
    public List<DriverStandings> getDriverStandings(int season, int round, int limit, int offset) throws IOException {
        String url = getUrl(DRIVER_STANDINGS_REQ, season, limit, offset);
        url = getResultsUrl(url, round);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races", "QualifyingResults"}, DriverStandings.class);
    }

    private String getResultsUrl(String url, int round) {
        return url.replace("{ROUND}", String.valueOf(round));
    }

    private String getUrl(String url, int season, int limit, int offset) {
        return url.
                replace("{SERIES}", "f1").
                replace("{SEASON}", season == -1 ? "" : String.valueOf(season)).
                replace("{LIMIT}", limit == -1 ? "30" : String.valueOf(limit)).
                replace("{OFFSET}", offset == -1 ? "0" : String.valueOf(offset)).
                replace("f1//", "f1/").
                replace("/.json", ".json");
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
