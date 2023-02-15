package com.aidaole.base.datas.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * /
 */
public class RespQrKey implements Serializable {

    @SerializedName("data")
    public DataEntity data;
    @SerializedName("code")
    public int code;

    public static class DataEntity {
        @SerializedName("code")
        public int code;
        @SerializedName("unikey")
        public String unikey;
    }
}
