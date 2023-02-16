package com.aidaole.base.datas.entities;

import com.google.gson.annotations.SerializedName;

public class RespCheckLoginQr {

    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;
    @SerializedName("cookie")
    public String cookie;

    @Override
    public String toString() {
        return "RespCheckLoginQr{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", cookie='" + cookie + '\'' +
                '}';
    }
}
