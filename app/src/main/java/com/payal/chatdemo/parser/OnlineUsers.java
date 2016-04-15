package com.payal.chatdemo.parser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by payal on 4/15/2016.
 */
public class OnlineUsers implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("n")
    private String n;

    @SerializedName("l")
    private String l;

    @SerializedName("a")
    private String a;

    @SerializedName("d")
    private String d;

    @SerializedName("s")
    private String s;

    @SerializedName("m")
    private String m;

    @SerializedName("u")
    private String u;

    @SerializedName("g")
    private String g;

    @SerializedName("ls")
    private String ls;

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getLs() {
        return ls;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    public String getLstn() {
        return lstn;
    }

    public void setLstn(String lstn) {
        this.lstn = lstn;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("lstn")
    private String lstn;

    @SerializedName("ch")
    private String ch;





}
