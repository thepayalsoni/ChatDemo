package com.payal.chatdemo.parser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by payal on 16/4/16.
 */
public class ChatSentResponse implements Serializable{



    @SerializedName("id")
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @SerializedName("m")
    String m;

    @SerializedName("from")
    long fromId;
    @SerializedName("direction")
    String direction;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("message")
    String message;



}
