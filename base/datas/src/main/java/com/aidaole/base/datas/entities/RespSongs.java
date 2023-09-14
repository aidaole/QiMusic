package com.aidaole.base.datas.entities;

import com.google.gson.annotations.SerializedName;

public class RespSongs {
    @SerializedName("code")
    public int code;
    @SerializedName("songs")
    public java.util.List<Song> songs;

    public static class Song {
        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public long id;
        @SerializedName("dt")
        public long dt; // 时长
        @SerializedName("al")
        public Al al;

        public int progress;

        public static class Al {
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

            @Override
            public String toString() {
                return "Al{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", picUrl='" + picUrl + '\'' +
                        ", picStr='" + picStr + '\'' +
                        ", pic=" + pic +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Song{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", dt=" + dt +
                    ", al=" + al +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RespSongs{" +
                "code=" + code +
                ", songs=" + songs +
                '}';
    }
}

