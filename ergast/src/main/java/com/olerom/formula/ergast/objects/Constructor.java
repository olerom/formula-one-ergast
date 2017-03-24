package com.olerom.formula.ergast.objects;

/**
 * Date: 09.03.17
 * Constructor object
 *
 * @author olerom
 */
public class Constructor {
    private String constructorId;
    private String url;
    private String name;
    private String nationality;

    public String getConstructorId() {
        return constructorId;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "Constructor{" +
                "constructorId='" + constructorId + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }

    public Constructor(String circuitId, String url, String name, String nationality) {
        this.constructorId = circuitId;
        this.url = url;
        this.name = name;
        this.nationality = nationality;
    }
}
