package ru.olerom.formula.ergast.objects;

/**
 * Date: 11.03.17
 * Time object
 *
 * @author olerom
 */
public class Time {
    private int millis;
    private String time;

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

    @Override
    public String toString() {
        return "Time{" +
                "millis=" + millis +
                ", time='" + time + '\'' +
                '}';
    }
}
