package com.aidaole.base.datas.entities;

import com.google.gson.annotations.SerializedName;

public class RespUserInfo {

    @SerializedName("code")
    public int code;
    @SerializedName("account")
    public AccountEntity account;
    @SerializedName("profile")
    public Object profile;

    @Override
    public String toString() {
        return "RespUserInfo{" +
                "code=" + code +
                ", account=" + account +
                ", profile=" + profile +
                '}';
    }

    public static class AccountEntity {
        @SerializedName("id")
        public long id;
        @SerializedName("userName")
        public String userName;
        @SerializedName("type")
        public int type;
        @SerializedName("status")
        public int status;
        @SerializedName("whitelistAuthority")
        public int whitelistAuthority;
        @SerializedName("createTime")
        public long createTime;
        @SerializedName("tokenVersion")
        public int tokenVersion;
        @SerializedName("ban")
        public int ban;
        @SerializedName("baoyueVersion")
        public int baoyueVersion;
        @SerializedName("donateVersion")
        public int donateVersion;
        @SerializedName("vipType")
        public int vipType;
        @SerializedName("anonimousUser")
        public boolean anonimousUser;
        @SerializedName("paidFee")
        public boolean paidFee;

        @Override
        public String toString() {
            return "AccountEntity{" +
                    "id=" + id +
                    ", userName='" + userName + '\'' +
                    ", type=" + type +
                    ", status=" + status +
                    ", whitelistAuthority=" + whitelistAuthority +
                    ", createTime=" + createTime +
                    ", tokenVersion=" + tokenVersion +
                    ", ban=" + ban +
                    ", baoyueVersion=" + baoyueVersion +
                    ", donateVersion=" + donateVersion +
                    ", vipType=" + vipType +
                    ", anonimousUser=" + anonimousUser +
                    ", paidFee=" + paidFee +
                    '}';
        }
    }
}
