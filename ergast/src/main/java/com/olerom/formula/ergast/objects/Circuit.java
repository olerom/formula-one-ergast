package com.olerom.formula.ergast.objects;

/**
 * Date: 09.03.17
 * Circuit object
 *
 * @author olerom
 */
public class Circuit {
    private String circuitId;
    private String url;
    private String circuitName;
    private Location location;

    public String getCircuitId() {
        return circuitId;
    }

    public String getUrl() {
        return url;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public Location getLocation() {
        return location;
    }

    public Circuit(String circuitId, String url, String circuitName, Location location) {
        this.circuitId = circuitId;
        this.url = url;
        this.circuitName = circuitName;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Circuit{" +
                "circuitId='" + circuitId + '\'' +
                ", url='" + url + '\'' +
                ", circuitName='" + circuitName + '\'' +
                ", location=" + location +
                '}';
    }
}
