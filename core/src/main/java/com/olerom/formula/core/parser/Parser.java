package com.olerom.formula.core.parser;

import com.google.gson.*;
import com.olerom.formula.core.objects.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 09.03.17
 *
 * @author olerom
 */
public class Parser {

    public static <T> List<T> parse(String json, String[] jsonObjects, Class<T> type) {
        json = fixJson(json, type);

        JsonArray jarray = getJsonArray(json, jsonObjects, type);
        List<T> entities = new ArrayList<>();

        Gson gson = getGson();
        for (int i = 0; i < jarray.size(); i++) {
            entities.add(gson.fromJson(jarray.get(i).getAsJsonObject(), type));
        }

        for (T entitySout : entities) {
            System.out.println(entitySout);
        }

        return entities;
    }

    private static JsonArray getJsonArray(String json, String[] jsonObjects, Type type) {
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("MRData");

        if (type == RaceResult.class || type == Qualification.class) {
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

    // TODO: optimize
    private static String fixJson(String json, Type type) {
        return json.
                replace("\"Location\"", "\"location\"").
                replace("\"Circuit\"", "\"circuit\"").
                replace("\"Constructor\"", "\"constructor\"").
                replace("\"Driver\"", "\"driver\"").
                replace("\"Time\"", "\"time\"").
                replace("\"AverageSpeed\"", "\"averageSpeed\"").
                replace("\"FastestLap\"", "\"fastestLap\"").
                replace("\"Q1\"", "\"q1\"").
                replace("\"Q2\"", "\"q2\"").
                replace("\"Q3\"", "\"q3\"");
    }

    // TODO: optimize
    private static Gson getGson() {
        return new GsonBuilder().
                registerTypeAdapter(Location.class, new Deserializer<Location>()).
                registerTypeAdapter(Circuit.class, new Deserializer<Circuit>()).
                registerTypeAdapter(Time.class, new Deserializer<Time>()).
                registerTypeAdapter(AverageSpeed.class, new Deserializer<AverageSpeed>()).
                registerTypeAdapter(Driver.class, new Deserializer<Driver>()).
                registerTypeAdapter(Constructor.class, new Deserializer<Constructor>()).
                create();
    }
}
