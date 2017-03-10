package com.olerom.formula.core.parser;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Date: 10.03.17
 *
 * @author olerom
 */
public class Deserializer<T> implements JsonDeserializer<T> {
    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonElement location = jsonElement.getAsJsonObject();

        return new Gson().fromJson(location, type);
    }
}