package com.olerom.formula.core.parser;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 09.03.17
 *
 * @author olerom
 */
public class Parser {

    public <T> List<T> parse(String json, String table, String entity, Class<T> type) {
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("MRData").getAsJsonObject(table);
        JsonArray jarray = jobject.getAsJsonArray(entity);
        List<T> entities = new ArrayList<>();
        for (int i = 0; i < jarray.size(); i++) {
            entities.add(new Gson().fromJson(jarray.get(i).getAsJsonObject(), type));
        }

        for (T entitySout : entities) {
            System.out.println(entitySout);
        }

        return entities;
    }
}
