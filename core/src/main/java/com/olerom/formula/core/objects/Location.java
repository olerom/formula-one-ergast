package com.olerom.formula.core.objects;

/**
 * Date: 09.03.17
 * Location object
 *
 * @author olerom
 */
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
