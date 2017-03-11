package com.olerom.formula.core.objects;

/**
 * Date: 10.03.17
 *
 * @author olerom
 */
public class RaceResult {
    private int number;
    private int position;
    private String positionText;
    private int points;
    private Driver driver;
    private Constructor constructor;

    private int grid;
    private int laps;
    private String status;
    private Time time;
    private FastestLap fastestLap;

    public RaceResult(int number, int position, String positionText,
                      int points, Driver driver, Constructor constructor,
                      int grid, int laps, String status, Time time, FastestLap fastestLap) {
        this.number = number;
        this.position = position;
        this.positionText = positionText;
        this.points = points;
        this.driver = driver;
        this.constructor = constructor;
        this.grid = grid;
        this.laps = laps;
        this.status = status;
        this.time = time;
        this.fastestLap = fastestLap;
    }

    public int getNumber() {
        return number;
    }

    public int getPosition() {
        return position;
    }

    public String getPositionText() {
        return positionText;
    }

    public int getPoints() {
        return points;
    }

    public Driver getDriver() {
        return driver;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public int getGrid() {
        return grid;
    }

    public int getLaps() {
        return laps;
    }

    public String getStatus() {
        return status;
    }

    public Time getTime() {
        return time;
    }

    public FastestLap getFastestLap() {
        return fastestLap;
    }

    @Override
    public String toString() {
        return "RaceResult{" +
                "number=" + number +
                ", position=" + position +
                ", positionText='" + positionText + '\'' +
                ", points=" + points +
                ", driver=" + driver +
                ", constructor=" + constructor +
                ", grid=" + grid +
                ", laps=" + laps +
                ", status='" + status + '\'' +
                ", time=" + time +
                ", fastestLap=" + fastestLap +
                '}';
    }
}
