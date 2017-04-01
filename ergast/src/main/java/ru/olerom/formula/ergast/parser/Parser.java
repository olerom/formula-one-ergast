package ru.olerom.formula.ergast.parser;

import com.google.gson.*;
import ru.olerom.formula.ergast.objects.ConstructorStandings;
import ru.olerom.formula.ergast.objects.DriverStandings;
import ru.olerom.formula.ergast.objects.Qualification;
import ru.olerom.formula.ergast.objects.RaceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 09.03.17
 *
 * @author olerom
 */
public class Parser<T> {
    private String json;
    private String[] jsonObjects;
    private Class<T> type;

    public Parser(String json, String[] jsonObjects, Class<T> type) {
        this.json = json;
        this.jsonObjects = jsonObjects;
        this.type = type;
    }

    public List<T> parse() {
        fixJson();

        JsonArray jarray = getJsonArray();
        List<T> entities = new ArrayList<>();

        Gson gson = new Gson();
        for (int i = 0; i < jarray.size(); i++) {
            entities.add(gson.fromJson(jarray.get(i).getAsJsonObject(), type));
        }

        return entities;
    }

    private JsonArray getJsonArray() {
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("MRData");

        if (type == RaceResult.class || type == Qualification.class
                || type == DriverStandings.class || type == ConstructorStandings.class) {
            for (int i = 0; i < jsonObjects.length - 2; i++) {
                jobject = jobject.getAsJsonObject(jsonObjects[i]);
            }
            jobject = jobject.getAsJsonArray(jsonObjects[jsonObjects.length - 2]).get(0).getAsJsonObject();
        } else {
            for (int i = 0; i < jsonObjects.length - 1; i++) {
                jobject = jobject.getAsJsonObject(jsonObjects[i]);
            }
        }
        return jobject.getAsJsonArray(jsonObjects[jsonObjects.length - 1]);
    }

    // TODO: optimize by using class type
    private void fixJson() {
        json = json.
                replace("\"Location\"", "\"location\"").
                replace("\"Circuit\"", "\"circuit\"").
                replace("\"Constructor\"", "\"constructor\"").
                replace("\"Driver\"", "\"driver\"").
                replace("\"Time\"", "\"time\"").
                replace("\"AverageSpeed\"", "\"averageSpeed\"").
                replace("\"FastestLap\"", "\"fastestLap\"").
                replace("\"Q1\"", "\"q1\"").
                replace("\"Q2\"", "\"q2\"").
                replace("\"Q3\"", "\"q3\"").
                replace("\"Constructors\"", "\"constructors\"").
                replace("\"Laps\"", "\"laps\"").
                replace("\"Timings\"", "\"timings\"").
                replace("\"PitStops\"", "\"pitStops\"");
    }
}
