package com.olerom.formula.core.objects;

/**
 * Date: 14.03.17
 *
 * @author olerom
 */
public class Timing {
    private String driverId;
    private int position;
    private String time;

    public Timing(String driverId, int position, String time) {
        this.driverId = driverId;
        this.position = position;
        this.time = time;
    }

    public String getDriverId() {
        return driverId;
    }

    public int getPosition() {
        return position;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Timing{" +
                "driverId='" + driverId + '\'' +
                ", position=" + position +
                ", time='" + time + '\'' +
                '}';
    }
}
