package com.aidaole.base.datas.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PlayListSongs implements Serializable {
    @SerializedName("code")
    public int code;
    @SerializedName("songs")
    public List<Songs> songs;

    public static class Songs implements Serializable {
        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public int id;
        @SerializedName("pst")
        public int pst;
        @SerializedName("t")
        public int t;
        @SerializedName("pop")
        public int pop;
        @SerializedName("st")
        public int st;
        @SerializedName("rt")
        public Object rt;
        @SerializedName("fee")
        public int fee;
        @SerializedName("v")
        public int v;
        @SerializedName("al")
        public Al al;
        @SerializedName("mv")
        public int mv;
        @SerializedName("publishTime")
        public long publishTime;

        public static class Al implements Serializable {
            @SerializedName("id")
            public int id;
            @SerializedName("name")
            public String name;
            @SerializedName("picUrl")
            public String picUrl;
            @SerializedName("pic_str")
            public String picStr;
            @SerializedName("pic")
            public long pic;
            @SerializedName("tns")
            public List<?> tns;
        }
    }
}
