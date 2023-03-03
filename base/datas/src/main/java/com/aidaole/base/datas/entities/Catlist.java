package com.aidaole.base.datas.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Catlist {
    @SerializedName("code")
    public int code;
    @SerializedName("all")
    public All all;
    @SerializedName("categories")
    public Categories categories;
    @SerializedName("sub")
    public List<Sub> sub;

    public static class All {
        @SerializedName("name")
        public String name;
        @SerializedName("resourceCount")
        public int resourceCount;
        @SerializedName("imgId")
        public int imgId;
        @SerializedName("imgUrl")
        public Object imgUrl;
        @SerializedName("type")
        public int type;
        @SerializedName("category")
        public int category;
        @SerializedName("resourceType")
        public int resourceType;
        @SerializedName("hot")
        public boolean hot;
        @SerializedName("activity")
        public boolean activity;
    }

    public static class Categories {
        @SerializedName("0")
        public String $0;
        @SerializedName("1")
        public String $1;
        @SerializedName("2")
        public String $2;
        @SerializedName("3")
        public String $3;
        @SerializedName("4")
        public String $4;
    }

    public static class Sub {
        @SerializedName("name")
        public String name;
        @SerializedName("resourceCount")
        public int resourceCount;
        @SerializedName("imgId")
        public int imgId;
        @SerializedName("imgUrl")
        public Object imgUrl;
        @SerializedName("type")
        public int type;
        @SerializedName("category")
        public int category;
        @SerializedName("resourceType")
        public int resourceType;
        @SerializedName("hot")
        public boolean hot;
        @SerializedName("activity")
        public boolean activity;
    }
}
