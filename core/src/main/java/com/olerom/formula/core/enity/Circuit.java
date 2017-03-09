package com.olerom.formula.core.enity;

/**
 * Date: 09.03.17
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

    public class Location {
        private float lat;
        private float lng;
        private String locality;
        private String country;

        public Location(float lat, float lng, String locality, String country) {
            this.lat = lat;
            this.lng = lng;
            this.locality = locality;
            this.country = country;
        }

        public Location(String lat, String lng, String locality, String country) {
            this.lat = Float.valueOf(lat);
            this.lng = Float.valueOf(lng);
            this.locality = locality;
            this.country = country;
        }
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
