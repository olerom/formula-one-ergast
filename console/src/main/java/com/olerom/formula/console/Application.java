package com.olerom.formula.console;

import com.olerom.formula.core.Ergast;
import com.olerom.formula.core.objects.*;

import java.io.IOException;
import java.util.List;

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
        Ergast ergast = new Ergast(2016, 30, 0);

        try {
            List<Circuit> circuits = ergast.getCircuits();
            List<Driver> drivers = ergast.getDrivers();
            List<Constructor> constructors = ergast.getConstructors();
            List<Season> seasons = ergast.getSeasons();
            List<Schedule> schedules = ergast.getSchedules();
            List<RaceResult> results = ergast.getRaceResults(2);
            List<Qualification> qualifications = ergast.getQualificationResults(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
