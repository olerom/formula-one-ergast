package com.olerom.formula.core.parser;

import com.google.gson.*;
import com.olerom.formula.core.objects.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 09.03.17
 *
 * @author olerom
 */
public class Parser {

    public static <T> List<T> parse(String json, String[] jsonObjects, Class<T> type) {
        json = fixJson(json);

        JsonElement jelement = new JsonParser().parse(json);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("MRData");
        for (int i = 0; i < jsonObjects.length - 1; i++) {
            jobject = jobject.getAsJsonObject(jsonObjects[i]);
        }
        JsonArray jarray = jobject.getAsJsonArray(jsonObjects[jsonObjects.length - 1]);
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

    private static String fixJson(String json) {
        return json.
                replace("\"Location\"", "\"location\"").
                replace("\"Circuit\"", "\"circuit\"").
                replace("\"Constructor\"", "\"constructor\"").
                replace("\"Driver\"", "\"driver\"").
                replace("\"Time\"", "\"time\"").
                replace("\"AverageSpeed\"", "\"averageSpeed\"");
    }

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
