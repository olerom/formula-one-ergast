package com.olerom.formula.core.parser;

import com.google.gson.*;
import com.olerom.formula.core.objects.Circuit;
import com.olerom.formula.core.objects.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 09.03.17
 *
 * @author olerom
 */
public class Parser {

    public static <T> List<T> parse(String json, String table, String entity, Class<T> type) {
        json = json.replace("Location", "location");
        json = json.replace("Circuit", "circuit");
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("MRData").getAsJsonObject(table);
        JsonArray jarray = jobject.getAsJsonArray(entity);
        List<T> entities = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Location.class, new Deserializer<Location>())
                .registerTypeAdapter(Circuit.class, new Deserializer<Circuit>())
                .create();
        for (int i = 0; i < jarray.size(); i++) {
            entities.add(gson.fromJson(jarray.get(i).getAsJsonObject(), type));
        }

        for (T entitySout : entities) {
            System.out.println(entitySout);
        }

        return entities;
    }
}
