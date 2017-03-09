package com.olerom.formula.core.objects;

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
