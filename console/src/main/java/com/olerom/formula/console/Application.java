package com.olerom.formula.console;

import com.olerom.formula.core.Ergast;

import java.io.IOException;

/**
 * Date: 08.03.17
 *
 * @author olerom
 */
public class Application {
    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        Ergast ergast = new Ergast();

        try {
            ergast.getDrivers(2016, 100, 0);
            ergast.getCircuits(2016, 100, 0);
            ergast.getConstructors(-1, 500, 0);
            ergast.getSeasons(-1, -1, -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
