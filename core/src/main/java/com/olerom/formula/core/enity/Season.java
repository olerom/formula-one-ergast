package com.olerom.formula.core.enity;

/**
 * Date: 09.03.17
 *
 * @author olerom
 */
public class Season {
    private int season;
    private String url;

    public Season(String season, String url) {
        this.season = Integer.valueOf(season);
        this.url = url;
    }
}
