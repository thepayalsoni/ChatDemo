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
        List<OnlineUsers> onlineUsersList = new ArrayList<OnlineUsers>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            OnlineUsers users = context.deserialize(entry.getValue(), OnlineUsers.class);
            onlineUsersList.add(users);
        }
        return new OnlineUsersList(onlineUsersList);
    }

}