package com.olerom.formula.core.objects;

import java.util.List;

/**
 * Date: 15.03.17
 *
 * @author olerom
 */
public class RacePitStops {
    private int season;
    private int round;
    private String url;
    private String raceName;
    private Circuit circuit;
    private String date;
    private String time;
    private List<PitStop> pitStops;

    public RacePitStops(int season, int round, String url, String raceName, Circuit circuit, String date, String time, List<PitStop> pitStops) {
        this.season = season;
        this.round = round;
        this.url = url;
        this.raceName = raceName;
        this.circuit = circuit;
        this.date = date;
        this.time = time;
        this.pitStops = pitStops;
    }

    public int getSeason() {
        return season;
    }

    public int getRound() {
        return round;
    }

    public String getUrl() {
        return url;
    }

    public String getRaceName() {
        return raceName;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public List<PitStop> getPitStops() {
        return pitStops;
    }

    @Override
    public String toString() {
        return "RacePitStops{" +
                "season=" + season +
                ", round=" + round +
                ", url='" + url + '\'' +
                ", raceName='" + raceName + '\'' +
                ", circuit=" + circuit +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", pitStops=" + pitStops +
                '}';
    }
}
