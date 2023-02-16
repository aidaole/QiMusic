package com.aidaole.base.datas.entities;

import com.google.gson.annotations.SerializedName;

public class RespQrImg {

    @SerializedName("code")
    public int code;
    @SerializedName("data")
    public DataEntity data;

    public static class DataEntity {
        @SerializedName("qrurl")
        public String qrurl;
        @SerializedName("qrimg")
        public String qrimg;

        @Override
        public String toString() {
            return "DataEntity{" +
                    "qrurl='" + qrurl + '\'' +
                    ", qrimg='" + qrimg + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RespQrImg{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
