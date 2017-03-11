package com.olerom.formula.core.objects;

/**
 * Date: 12.03.17
 *
 * @author olerom
 */
public class Qualification {
    private int number;
    private int position;
    private Driver driver;
    private Constructor constructor;
    private String q1;
    private String q2;
    private String q3;

    public Qualification(int number, int position, Driver driver, Constructor constructor, String q1, String q2, String q3) {
        this.number = number;
        this.position = position;
        this.driver = driver;
        this.constructor = constructor;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }

    public int getNumber() {
        return number;
    }

    public int getPosition() {
        return position;
    }

    public Driver getDriver() {
        return driver;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public String getQ1() {
        return q1;
    }

    public String getQ2() {
        return q2;
    }

    public String getQ3() {
        return q3;
    }

    @Override
    public String toString() {
        return "Qualification{" +
                "number=" + number +
                ", position=" + position +
                ", driver=" + driver +
                ", constructor=" + constructor +
                ", q1='" + q1 + '\'' +
                ", q2='" + q2 + '\'' +
                ", q3='" + q3 + '\'' +
                '}';
    }
}
