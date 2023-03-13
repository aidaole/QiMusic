package com.aidaole.base.datas.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RespUserInfo implements Serializable {

    @SerializedName("code")
    public int code;
    @SerializedName("account")
    public AccountEntity account;
    @SerializedName("profile")
    public ProfileEntity profile;

    public static class AccountEntity implements Serializable {
        @SerializedName("id")
        public int id;
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

    public static class ProfileEntity implements Serializable {
        @SerializedName("userId")
        public long userId;
        @SerializedName("userType")
        public int userType;
        @SerializedName("nickname")
        public String nickname;
        @SerializedName("avatarImgId")
        public long avatarImgId;
        @SerializedName("avatarUrl")
        public String avatarUrl;
        @SerializedName("backgroundImgId")
        public long backgroundImgId;
        @SerializedName("backgroundUrl")
        public String backgroundUrl;
        @SerializedName("signature")
        public String signature;
        @SerializedName("createTime")
        public long createTime;
        @SerializedName("userName")
        public String userName;
        @SerializedName("accountType")
        public int accountType;
        @SerializedName("shortUserName")
        public String shortUserName;
        @SerializedName("birthday")
        public long birthday;
        @SerializedName("authority")
        public int authority;
        @SerializedName("gender")
        public int gender;
        @SerializedName("accountStatus")
        public int accountStatus;
        @SerializedName("province")
        public int province;
        @SerializedName("city")
        public int city;
        @SerializedName("authStatus")
        public int authStatus;
        @SerializedName("description")
        public Object description;
        @SerializedName("detailDescription")
        public Object detailDescription;
        @SerializedName("defaultAvatar")
        public boolean defaultAvatar;
        @SerializedName("expertTags")
        public Object expertTags;
        @SerializedName("experts")
        public Object experts;
        @SerializedName("djStatus")
        public int djStatus;
        @SerializedName("locationStatus")
        public int locationStatus;
        @SerializedName("vipType")
        public int vipType;
        @SerializedName("followed")
        public boolean followed;
        @SerializedName("mutual")
        public boolean mutual;
        @SerializedName("authenticated")
        public boolean authenticated;
        @SerializedName("lastLoginTime")
        public long lastLoginTime;
        @SerializedName("lastLoginIP")
        public String lastLoginIP;
        @SerializedName("remarkName")
        public Object remarkName;
        @SerializedName("viptypeVersion")
        public long viptypeVersion;
        @SerializedName("authenticationTypes")
        public int authenticationTypes;
        @SerializedName("avatarDetail")
        public Object avatarDetail;
        @SerializedName("anchor")
        public boolean anchor;

        @Override
        public String toString() {
            return "ProfileEntity{" +
                    "userId=" + userId +
                    ", userType=" + userType +
                    ", nickname='" + nickname + '\'' +
                    ", avatarImgId=" + avatarImgId +
                    ", avatarUrl='" + avatarUrl + '\'' +
                    ", backgroundImgId=" + backgroundImgId +
                    ", backgroundUrl='" + backgroundUrl + '\'' +
                    ", signature='" + signature + '\'' +
                    ", createTime=" + createTime +
                    ", userName='" + userName + '\'' +
                    ", accountType=" + accountType +
                    ", shortUserName='" + shortUserName + '\'' +
                    ", birthday=" + birthday +
                    ", authority=" + authority +
                    ", gender=" + gender +
                    ", accountStatus=" + accountStatus +
                    ", province=" + province +
                    ", city=" + city +
                    ", authStatus=" + authStatus +
                    ", description=" + description +
                    ", detailDescription=" + detailDescription +
                    ", defaultAvatar=" + defaultAvatar +
                    ", expertTags=" + expertTags +
                    ", experts=" + experts +
                    ", djStatus=" + djStatus +
                    ", locationStatus=" + locationStatus +
                    ", vipType=" + vipType +
                    ", followed=" + followed +
                    ", mutual=" + mutual +
                    ", authenticated=" + authenticated +
                    ", lastLoginTime=" + lastLoginTime +
                    ", lastLoginIP='" + lastLoginIP + '\'' +
                    ", remarkName=" + remarkName +
                    ", viptypeVersion=" + viptypeVersion +
                    ", authenticationTypes=" + authenticationTypes +
                    ", avatarDetail=" + avatarDetail +
                    ", anchor=" + anchor +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RespUserInfo{" +
                "code=" + code +
                ", account=" + account +
                ", profile=" + profile +
                '}';
    }
}
