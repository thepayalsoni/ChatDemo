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

public class ChatroomsDeserializer implements JsonDeserializer<ChatroomsList> {

    @Override
    public ChatroomsList deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = element.getAsJsonObject();
        List<Chatrooms> chatrooms = new ArrayList<Chatrooms>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            Chatrooms chatroom = context.deserialize(entry.getValue(), Chatrooms.class);
            chatrooms.add(chatroom);
        }
        return new ChatroomsList(chatrooms);
    }

}