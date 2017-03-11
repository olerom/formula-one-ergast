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
        Ergast ergast = new Ergast();

        try {
//            List<Circuit> circuits = ergast.getCircuits(2016, 10, 0);
//            List<Driver> drivers = ergast.getDrivers(2016, 10, 0);
//            List<Constructor> constructors = ergast.getConstructors(-1, 10, 0);
//            List<Season> seasons = ergast.getSeasons(-1, 10, -1);
//            List<Schedule> schedules = ergast.getSchedules(-1, 10, -1);
//            List<RaceResult> results = ergast.getRaceResults(2016, 21, -1, -1);
            List<Qualification> qualifications = ergast.getQualificationResults(2016, 2, -1, -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
