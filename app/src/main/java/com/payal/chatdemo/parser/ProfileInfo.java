package com.payal.chatdemo.parser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by payal on 16/4/16.
 */
public class ProfileInfo implements Serializable {



    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;

    @SerializedName("a")
    String a;

    @SerializedName("l")
    String link;

    @SerializedName("s")
    String s;

    @SerializedName("m")
    String m;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getPush_channel() {
        return push_channel;
    }

    public void setPush_channel(String push_channel) {
        this.push_channel = push_channel;
    }

    public String getPush_an_channel() {
        return push_an_channel;
    }

    public void setPush_an_channel(String push_an_channel) {
        this.push_an_channel = push_an_channel;
    }

    @SerializedName("push_channel")
    String push_channel;


    @SerializedName("push_an_channel")
    String push_an_channel;




}
