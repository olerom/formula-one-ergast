package com.olerom.formula.core;

import com.olerom.formula.core.exceptions.QueryLimitException;
import com.olerom.formula.core.exceptions.QueryOffsetException;
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
    private final static String BASE_REQ = "http://ergast.com/api/{SERIES}/{SEASON}/{ROUND}/{REQUEST}.json?limit={LIMIT}&offset={OFFSET}";
    private final static String DRIVERS = "drivers";
    private final static String CIRCUITS = "circuits";
    private final static String CONSTRUCTORS = "constructors";
    private final static String SEASONS = "seasons";
    private final static String SCHEDULE = "";
    private final static String RESULTS = "results";
    private final static String QUALIFYING = "qualifying";
    private final static String DRIVER_STANDINGS = "driverStandings";
    private final static String CONSTRUCTOR_STANDINGS = "constructorStandings";
    private final static String FINISHING_STATUS = "status";
    private final static String LAP_TIMES = "laps";
    private final static String PIT_STOPS = "pitstops";

    private String series;
    private int season;
    private int limit;
    private int offset;

    public final static int NO_SEASON = -1;
    public final static int DEFAULT_LIMIT = 30;
    public final static int DEFAULT_OFFSET = 0;
    public final static int NO_ROUND = -1;

    /**
     * @param season is a season which you want to get, NO_SEASON if you want to get all the seasons.
     * @param limit  is a number of results that are returned. up to a maximum value of 1000.
     *               Please use the smallest value that your application needs. The default value is 30.
     * @param offset specifies an offset into the result set. The default value is 0.
     */
    public Ergast(int season, int limit, int offset) {
        if (limit > 1000) {
            throw new QueryLimitException("Limit requires to be less than 1000");
        } else if (limit < -1) {
            throw new QueryLimitException("Limit requires to be a positive number");
        } else if (offset < 0) {
            throw new QueryOffsetException("Offset requires t0 be a positive number");
        }
        this.season = season;
        this.limit = limit;
        this.offset = offset;
        this.series = "f1";
    }

    public Ergast() {
        this.offset = DEFAULT_OFFSET;
        this.limit = DEFAULT_LIMIT;
        this.season = NO_SEASON;
        this.series = "f1";
    }

    /**
     * @return list of drivers that satisfy your query.
     */
    public List<Driver> getDrivers() throws IOException {
        String url = buildUrl(DRIVERS, NO_ROUND);
        String json = getJson(url);
        return parse(json, new String[]{"DriverTable", "Drivers"}, Driver.class);
    }

    /**
     * @return list of circuits that satisfy your query.
     */
    public List<Circuit> getCircuits() throws IOException {
        String url = buildUrl(CIRCUITS, NO_ROUND);
        String json = getJson(url).replace("long", "lng");
        return parse(json, new String[]{"CircuitTable", "Circuits"}, Circuit.class);
    }

    /**
     * @return list of seasons that satisfy your query.
     */
    public List<Season> getSeasons() throws IOException {
        String url = buildUrl(SEASONS, NO_ROUND);
        String json = getJson(url);
        return parse(json, new String[]{"SeasonTable", "Seasons"}, Season.class);
    }

    /**
     * @return list of constructors that satisfy your query.
     */
    public List<Constructor> getConstructors() throws IOException {
        String url = buildUrl(CONSTRUCTORS, NO_ROUND);
        String json = getJson(url);
        return parse(json, new String[]{"ConstructorTable", "constructors"}, Constructor.class);
    }

    /**
     * @return list of schedules that satisfy your query.
     */
    public List<Schedule> getSchedules() throws IOException {
        String url = buildUrl(SCHEDULE, NO_ROUND);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races"}, Schedule.class);
    }

    /**
     * @param round is a round which you want to get.
     * @return list of race results that satisfy your query.
     */
    public List<RaceResult> getRaceResults(int round) throws IOException {
        if (this.season == NO_SEASON) {
            throw new SeasonException("Race results requires season to be mentioned");
        }

        String url = buildUrl(RESULTS, round);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races", "Results"}, RaceResult.class);
    }

    /**
     * @param round is a round which you want to get.
     * @return list of qualification results that satisfy your query.
     */
    public List<Qualification> getQualificationResults(int round) throws IOException {
        if (this.season == NO_SEASON) {
            throw new SeasonException("Qualification results requires season to be mentioned");
        }

        String url = buildUrl(QUALIFYING, round);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races", "QualifyingResults"}, Qualification.class);
    }

    /**
     * @param round is a round which you want to get, (-1) if you want to get whole season.
     * @return list of driver standings that satisfy your query.
     */
    public List<DriverStandings> getDriverStandings(int round) throws IOException {
        if (this.season == NO_SEASON) {
            throw new SeasonException("Driver standing requires season to be mentioned");
        }

        String url = buildUrl(DRIVER_STANDINGS, round);
        String json = getJson(url);
        return parse(json, new String[]{"StandingsTable", "StandingsLists", "DriverStandings"}, DriverStandings.class);
    }

    /**
     * @param round is a round which you want to get, (-1) if you want to get whole season.
     * @return list of constructor standings that satisfy your query.
     */
    public List<ConstructorStandings> getConstructorStandings(int round) throws IOException {
        if (this.season == NO_SEASON) {
            throw new SeasonException("Constructor standing requires season to be mentioned");
        }

        String url = buildUrl(CONSTRUCTOR_STANDINGS, round);
        String json = getJson(url);
        return parse(json, new String[]{"StandingsTable", "StandingsLists", "ConstructorStandings"}, ConstructorStandings.class);
    }

    /**
     * @param round is a round which you want to get, (-1) if you want to get whole season.
     * @return list of finishing statuses standings that satisfy your query.
     */
    public List<FinishingStatus> getFinishingstatuses(int round) throws IOException {
        if (this.season == NO_SEASON && round != NO_ROUND) {
            throw new SeasonException("Finishing status request requires season to be mentioned if you mention round");
        }

        String url = buildUrl(FINISHING_STATUS, round);
        String json = getJson(url);
        return parse(json, new String[]{"StatusTable", "Status"}, FinishingStatus.class);
    }

    /**
     * @param round is a round which you want to get.
     * @return list of lap times that satisfy your query.
     */
    public List<LapTimes> getLapTimes(int round) throws IOException {
        if (this.season == NO_SEASON || round == NO_ROUND) {
            throw new SeasonException("Finishing status request requires season and round to be mentioned");
        }

        String url = buildUrl(LAP_TIMES, round);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races"}, LapTimes.class);
    }

    /**
     * @param round is a round which you want to get.
     * @return list of pit stops that satisfy your query.
     */
    public List<RacePitStops> getRacePitStops(int round) throws IOException {
        if (this.season == NO_SEASON || round == NO_ROUND) {
            throw new SeasonException("Race pit stops request requires season and round to be mentioned");
        }

        String url = buildUrl(PIT_STOPS, round);
        String json = getJson(url);
        return parse(json, new String[]{"RaceTable", "Races"}, RacePitStops.class);
    }

    private String buildUrl(String request, int round) {
        return BASE_REQ.
                replace("{SERIES}", this.series).
                replace("{SEASON}", this.season == NO_SEASON ? "" : String.valueOf(this.season)).
                replace("{LIMIT}", String.valueOf(this.limit)).
                replace("{OFFSET}", String.valueOf(this.offset)).
                replace("{REQUEST}", request).
                replace(this.series + "//", this.series + "/").
                replace("{ROUND}/", round == NO_ROUND ? "" : String.valueOf(round) + "/").
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
        if (limit > 1000) {
            throw new QueryLimitException("Limit requires to be less than 1000");
        } else if (limit < -1) {
            throw new QueryLimitException("Limit requires to be a positive number");
        }

        this.limit = limit;
    }

    public void setOffset(int offset) {
        if (offset < 0) {
            throw new QueryOffsetException("Offset requires t0 be a positive number");
        }
        this.offset = offset;
    }

}
