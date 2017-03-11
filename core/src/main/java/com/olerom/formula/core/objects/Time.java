package com.olerom.formula.core.objects;

/**
 * Date: 11.03.17
 *
 * @author olerom
 */
public class Time {
    private int millis;
    private String time;

    public Time(String time) {
        this.millis = -1;
        this.time = time;
    }

    public Time(int millis, String time) {
        this.millis = millis;
        this.time = time;
    }

    public int getMillis() {
        return millis;
    }

    public String getTime() {
        return time;
    }
}
