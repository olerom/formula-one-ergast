package ru.olerom.formula.ergast.objects;

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

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public String getLocality() {
        return locality;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Location{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", locality='" + locality + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
