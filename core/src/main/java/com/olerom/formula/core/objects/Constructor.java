package com.olerom.formula.core.objects;

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
