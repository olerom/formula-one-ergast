package com.olerom.formula.core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Date: 08.03.17
 *
 * @author olerom
 */
public class Parser {
    public void parse(String json) throws Exception {
        JSONObject obj = new JSONObject(json);

        JSONArray arr = obj.getJSONObject("MRData").getJSONObject("DriverTable").getJSONArray("Drivers");
        ArrayList<Driver> drivers = new ArrayList<>(arr.length());
        for (int i = 0; i < arr.length(); i++) {
            drivers.add(new Driver(arr.getJSONObject(i).getString("familyName"),
                    arr.getJSONObject(i).getString("nationality")));
        }

        for (Driver driver : drivers) {
            System.out.println(driver.getName() + ": " + driver.getNationality());
        }
    }
}
