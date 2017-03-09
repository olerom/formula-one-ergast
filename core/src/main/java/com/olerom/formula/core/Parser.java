package com.olerom.formula.core;

import com.google.gson.*;
import com.olerom.formula.core.enity.Circuit;
import com.olerom.formula.core.enity.Driver;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 08.03.17
 *
 * @author olerom
 */
public class Parser {
    public void parseDriver(String json) throws Exception {
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("MRData").getAsJsonObject("DriverTable");
        JsonArray jarray = jobject.getAsJsonArray("Drivers");

        List<Driver> drivers = new ArrayList<>();
        for (int i = 0; i < jarray.size(); i++) {
            drivers.add(new Gson().fromJson(jarray.get(i).getAsJsonObject(), Driver.class));
        }

        for (Driver driver : drivers) {
            System.out.println(driver);
        }
    }

    public void parseCircuit(String json) {
        json = json.replaceAll("long", "lng");
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("MRData").getAsJsonObject("CircuitTable");
        JsonArray jarray = jobject.getAsJsonArray("Circuits");

        List<Circuit> circuits = new ArrayList<>();
        for (int i = 0; i < jarray.size(); i++) {
//            Location loc = new Location(new Gson().fromJson(jarray.get(i).getAsJsonObject().getAsJsonObject("location"),
//                    Location.class));

            circuits.add(new Gson().fromJson(jarray.get(i).getAsJsonObject(), Circuit.class));
        }

        for (Circuit circuit : circuits)
            System.out.println(circuit);

    }
}
