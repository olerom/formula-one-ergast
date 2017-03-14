package com.olerom.formula.core;

import com.olerom.formula.core.exceptions.SeasonException;
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
    private final static String CONSTRUCTOR_STANDINGS_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/{ROUND}/constructorStandings.json";
    private final static String FINISHING_STATUS_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/{ROUND}/status.json";
    private final static String LAPTIMES_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/{ROUND}/laps.json";
    private final static String PITSTOPS_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/{ROUND}/pitstops.json";

    private String series;
    private int season;
    private int limit;
    private int offset;

    /**
     * @param season is a season which you want to get, (-1) if you want to get all the seasons.
     * @param limit  is a number of results that are returned. up to a maximum value of 1000.
     *               Please use the smallest value that your application needs. If (-1), the default value is 30.
     * @param offset specifies an offset into the result set. If (-1), the default value is 30.
     */
    public Ergast(int season, int limit, int offset) {
        this.season = season;
        this.limit = limit;
        this.offset = offset;
        this.series = "f1";
    }

    public Ergast() {
        this.offset = 0;
        this.limit = 30;
        this.season = -1;
        this.series = "f1";
    }

    /**
     * @return list of drivers that satisfy your query.
     */
    public List<Driver> getDrivers() throws IOException {
        String url = getUrl(DRIVERS_REQ);
        String json = getJson(url);
        return parse(json, new String[]{"DriverTable", "Drivers"}, Driver.class);
    }

    /**
     * @return list of circuits that satisfy your query.
     */
    public List<Circuit> getCircuits() throws IOException {
        String url = getUrl(CIRCUITS_REQ);
        String json = getJson(url).replace("long", "lng");
        return parse(json, new String[]{"CircuitTable", "Circuits"}, Circuit.class);
    }

    /**
     * @return list of seasons that satisfy your query.
     */
    public List<Season> getSeasons() throws IOException {
        String url = getUrl(SEASONS_REQ);
        String json = getJson(url);
        return parse(json, new String[]{"SeasonTable", "Seasons"}, Season.class);
    }

    /**
     * @return list of constructors that satisfy your query.
     */
    public List<Constructor> getConstructors() throws IOException {
        String url = getUrl(CONSTRUCTORS_REQ);
        String json = getJson(url);
        return parse(json, new String[]{"ConstructorTable", "Constructors"}, Constructor.class);
    }

    /**
     * @return list of schedules that satisfy your query.
     */
    public List<Schedule> getSchedules() throws IOException {
        String url = getUrl(SCHEDULE_REQ);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races"}, Schedule.class);
    }

    /**
     * @param round is a round which you want to get.
     * @return list of race results that satisfy your query.
     */
    public List<RaceResult> getRaceResults(int round) throws IOException {
        if (this.season == -1) {
            throw new SeasonException("Race results requires season to be mentioned");
        }

        String url = getUrl(RESULTS_REQ);
        url = getResultsUrl(url, round);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races", "Results"}, RaceResult.class);
    }

    /**
     * @param round is a round which you want to get.
     * @return list of qualification results that satisfy your query.
     */
    public List<Qualification> getQualificationResults(int round) throws IOException {
        if (this.season == -1) {
            throw new SeasonException("Qualification results requires season to be mentioned");
        }

        String url = getUrl(QUALIFYING_REQ);
        url = getResultsUrl(url, round);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races", "QualifyingResults"}, Qualification.class);
    }

    /**
     * @param round is a round which you want to get, (-1) if you want to get whole season.
     * @return list of driver standings that satisfy your query.
     */
    public List<DriverStandings> getDriverStandings(int round) throws IOException {
        if (this.season == -1) {
            throw new SeasonException("Driver standing requires season to be mentioned");
        }

        String url = getUrl(DRIVER_STANDINGS_REQ);
        url = getResultsUrl(url, round);
        String json = getJson(url);
        return parse(json, new String[]{"StandingsTable", "StandingsLists", "DriverStandings"}, DriverStandings.class);
    }

    /**
     * @param round is a round which you want to get, (-1) if you want to get whole season.
     * @return list of constructor standings that satisfy your query.
     */
    public List<ConstructorStandings> getConstructorStandings(int round) throws IOException {
        if (this.season == -1) {
            throw new SeasonException("Constructor standing requires season to be mentioned");
        }

        String url = getUrl(CONSTRUCTOR_STANDINGS_REQ);
        url = getResultsUrl(url, round);
        String json = getJson(url);
        return parse(json, new String[]{"StandingsTable", "StandingsLists", "ConstructorStandings"}, ConstructorStandings.class);
    }

    /**
     * @param round is a round which you want to get, (-1) if you want to get whole season.
     * @return list of finishing statuses standings that satisfy your query.
     */
    public List<FinishingStatus> getFinishingstatuses(int round) throws IOException {
        if (this.season == -1 && round != -1) {
            throw new SeasonException("Finishing status request requires season to be mentioned if you mention round");
        }

        String url = getUrl(FINISHING_STATUS_REQ);
        url = getResultsUrl(url, round);
        String json = getJson(url);
        return parse(json, new String[]{"StatusTable", "Status"}, FinishingStatus.class);
    }

    /**
     * @param round is a round which you want to get.
     * @return list of lap times that satisfy your query.
     */
    public List<LapTimes> getLapTimes(int round) throws IOException {
        if (this.season == -1 || round == -1) {
            throw new SeasonException("Finishing status request requires season and round to be mentioned");
        }

        String url = getUrl(LAPTIMES_REQ);
        url = getResultsUrl(url, round);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races"}, LapTimes.class);
    }

    /**
     * @param round is a round which you want to get.
     * @return list of pit stops that satisfy your query.
     */
    public List<RacePitStops> getRacePitStops(int round) throws IOException {
        if (this.season == -1 || round == -1) {
            throw new SeasonException("Race pit stops request requires season and round to be mentioned");
        }

        String url = getUrl(PITSTOPS_REQ);
        url = getResultsUrl(url, round);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races"}, RacePitStops.class);
    }

    private String getResultsUrl(String url, int round) {
        return url.replace("{ROUND}/", round == -1 ? "" : String.valueOf(round) + "/");
    }

    private String getUrl(String url) {
        return url.
                replace("{SERIES}", this.series).
                replace("{SEASON}", this.season == -1 ? "" : String.valueOf(this.season)).
                replace("{LIMIT}", this.limit == -1 ? "30" : String.valueOf(this.limit)).
                replace("{OFFSET}", this.offset == -1 ? "0" : String.valueOf(this.offset)).
                replace(this.series + "//", this.series + "/").
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

    public String getSeries() {
        return series;
    }

    public int getSeason() {
        return season;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
