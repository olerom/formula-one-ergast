package com.olerom.formula.ergast.objects;

import java.util.List;

/**
 * Date: 12.03.17
 *
 * @author olerom
 */
public class DriverStandings {
    private int position;
    private String positionText;
    private int points;
    private int wins;
    private Driver driver;
    private List<Constructor> constructors;

    public DriverStandings(int position, String positionText, int points, int wins, Driver driver, List<Constructor> constructors) {
        this.position = position;
        this.positionText = positionText;
        this.points = points;
        this.wins = wins;
        this.driver = driver;
        this.constructors = constructors;
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

    public int getWins() {
        return wins;
    }

    public Driver getDriver() {
        return driver;
    }

    public List<Constructor> getConstructors() {
        return constructors;
    }

    @Override
    public String toString() {
        return "DriverStandings{" +
                "position=" + position +
                ", positionText='" + positionText + '\'' +
                ", points=" + points +
                ", wins=" + wins +
                ", driver=" + driver +
                ", constructors=" + constructors +
                '}';
    }
}
