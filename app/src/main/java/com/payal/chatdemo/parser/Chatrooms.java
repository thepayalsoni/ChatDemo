package com.payal.chatdemo.parser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by payal on 4/15/2016.
 */

public class Chatrooms implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @SerializedName("online")
    private String online;

    @SerializedName("type")
    private String type;

    @SerializedName("i")
    private String i;

    @SerializedName("s")
    private String s;


}
