package ru.olerom.formula.ergast.objects;

import java.util.List;

/**
 * Date: 14.03.17
 *
 * @author olerom
 */
public class LapTimes {
    private int season;
    private int round;
    private String url;
    private String raceName;
    private Circuit circuit;
    private String date;
    private String time;
    private List<Lap> laps;

    public LapTimes(int season, int round, String url, String raceName, Circuit circuit, String date, String time, List<Lap> laps) {
        this.season = season;
        this.round = round;
        this.url = url;
        this.raceName = raceName;
        this.circuit = circuit;
        this.date = date;
        this.time = time;
        this.laps = laps;
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

    public List<Lap> getLaps() {
        return laps;
    }

    @Override
    public String toString() {
        return "LapTimes{" +
                "season=" + season +
                ", round=" + round +
                ", url='" + url + '\'' +
                ", raceName='" + raceName + '\'' +
                ", circuit=" + circuit +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", laps=" + laps +
                '}';
    }
}
