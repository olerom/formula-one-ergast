package com.olerom.formula.core;

/**
 * Date: 08.03.17
 *
 * @author olerom
 */
public class Driver {
    private String name;
    private String nationality;

    public Driver(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }
}
