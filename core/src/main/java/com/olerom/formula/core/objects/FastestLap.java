package com.olerom.formula.core.objects;

/**
 * Date: 11.03.17
 *
 * @author olerom
 */
public class FastestLap {
    private int rank;
    private int lap;
    private Time time;
    private AverageSpeed averageSpeed;

    public FastestLap(int rank, int lap, Time time, AverageSpeed averageSpeed) {
        this.rank = rank;
        this.lap = lap;
        this.time = time;
        this.averageSpeed = averageSpeed;
    }

    public int getRank() {
        return rank;
    }

    public int getLap() {
        return lap;
    }

    public Time getTime() {
        return time;
    }

    public AverageSpeed getAverageSpeed() {
        return averageSpeed;
    }
}
