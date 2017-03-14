package com.olerom.formula.core.objects;

/**
 * Date: 15.03.17
 *
 * @author olerom
 */
public class PitStop {
    private String driverId;
    private int stop;
    private int lap;
    private String time;
    private String duration;

    public PitStop(String driverId, int stop, int lap, String time, String duration) {
        this.driverId = driverId;
        this.stop = stop;
        this.lap = lap;
        this.time = time;
        this.duration = duration;
    }

    public String getDriverId() {
        return driverId;
    }

    public int getStop() {
        return stop;
    }

    public int getLap() {
        return lap;
    }

    public String getTime() {
        return time;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "PitStop{" +
                "driverId='" + driverId + '\'' +
                ", stop=" + stop +
                ", lap=" + lap +
                ", time='" + time + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
