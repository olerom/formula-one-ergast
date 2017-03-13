package com.olerom.formula.core.objects;

/**
 * Date: 12.03.17
 *
 * @author olerom
 */
public class ConstructorStandings {
    private int position;
    private String positionText;
    private int points;
    private int wins;
    private Constructor constructor;

    public ConstructorStandings(int position, String positionText, int points, int wins, Constructor constructor) {
        this.position = position;
        this.positionText = positionText;
        this.points = points;
        this.wins = wins;
        this.constructor = constructor;
    }

    @Override
    public String toString() {
        return "ConstructorStandings{" +
                "position=" + position +
                ", positionText='" + positionText + '\'' +
                ", points=" + points +
                ", wins=" + wins +
                ", constructor=" + constructor +
                '}';
    }
}
