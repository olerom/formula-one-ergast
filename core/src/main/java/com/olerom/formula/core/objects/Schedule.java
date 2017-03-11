package com.olerom.formula.core.objects;

/**
 * Date: 10.03.17
 *
 * @author olerom
 */
public class Schedule {
    private int season;
    private int round;
    private String url;
    private String raceName;
    private Circuit circuit;
    private String date;
    private String time;

    public Schedule(int season, int round, String url, String raceName, Circuit circuit, String date, String time) {
        this.season = season;
        this.round = round;
        this.url = url;
        this.raceName = raceName;
        this.circuit = circuit;
        this.date = date;
        this.time = time;
    }

    public Schedule(String season, String round, String url, String raceName, Circuit circuit, String date, String time) {
        this.season = Integer.valueOf(season);
        this.round = Integer.valueOf(round);
        this.url = url;
        this.raceName = raceName;
        this.circuit = circuit;
        this.date = date;
        this.time = time;
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

    @Override
    public String toString() {
        return "Schedule{" +
                "season=" + season +
                ", round=" + round +
                ", url='" + url + '\'' +
                ", raceName='" + raceName + '\'' +
                ", circuit=" + circuit +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
