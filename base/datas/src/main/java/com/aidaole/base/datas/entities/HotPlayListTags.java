package com.aidaole.base.datas.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotPlayListTags {
    @SerializedName("code")
    public int code;
    @SerializedName("tags")
    public List<Tags> tags;

    public static class Tags {
        @SerializedName("playlistTag")
        public PlaylistTag playlistTag;
        @SerializedName("activity")
        public boolean activity;
        @SerializedName("usedCount")
        public int usedCount;
        @SerializedName("hot")
        public boolean hot;
        @SerializedName("createTime")
        public long createTime;
        @SerializedName("position")
        public int position;
        @SerializedName("category")
        public int category;
        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public int id;
        @SerializedName("type")
        public int type;

        public static class PlaylistTag {
            @SerializedName("id")
            public int id;
            @SerializedName("name")
            public String name;
            @SerializedName("category")
            public int category;
            @SerializedName("usedCount")
            public int usedCount;
            @SerializedName("type")
            public int type;
            @SerializedName("position")
            public int position;
            @SerializedName("createTime")
            public long createTime;
            @SerializedName("highQuality")
            public int highQuality;
            @SerializedName("highQualityPos")
            public int highQualityPos;
            @SerializedName("officialPos")
            public int officialPos;
        }
    }
}
