package com.payal.chatdemo.parser;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OnlineUsersDeserializer implements JsonDeserializer<OnlineUsersList> {

    @Override
    public OnlineUsersList deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = element.getAsJsonObject();
        List<OnlineUsers> cities = new ArrayList<OnlineUsers>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            // For individual City objects, we can use default deserialisation:
            OnlineUsers city = context.deserialize(entry.getValue(), OnlineUsers.class);
            cities.add(city);
        }
        return new OnlineUsersList(cities);
    }

}