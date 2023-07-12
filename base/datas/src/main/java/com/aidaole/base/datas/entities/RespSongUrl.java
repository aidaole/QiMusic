package com.aidaole.base.datas.entities;

import java.io.Serializable;
import java.util.List;

public class RespSongUrl implements Serializable {

    public List<Data> data;
    public int code;

    public static class Data {
        public int id;
        public String url;
        public int br;
        public int size;
        public String md5;
        public int code;
        public String type;
        public double gain;
        public int peak;
        public int fee;
        public int payed;
        public String level;
        public String encodeType;
        public int time;
    }
}
